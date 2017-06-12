package Sites;

import Base.CoinInfo;
import Base.IBitcoinSiteApi;
import Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BithumbApi implements IBitcoinSiteApi
{
    private final String tokenMinPrice = "min_price";
    private final String tokenMaxPrice = "max_price";
    private final String tokenAvgPrice = "average_price";

    private final String requestURL = "https://api.bithumb.com/public/ticker/";

    JSONParser jsonParser;
    CoinInfo[] Infomation;

    public BithumbApi()
    {
        jsonParser = new JSONParser();
        Infomation = new CoinInfo[6];
    }

    private String CoinTypeToToken(EnumCoinTypes type)
    {
        switch(type)
        {
        case Bitcoin            : return "BTC";
        case Etherium           : return "ETH";
        case Dash               : return "DASH";
        case LightCoin          : return "LTC";
        case EtheriumClassic    : return "ETC";
        case Ripple             : return "XRP";
        default : return "";
        }
    }

    public void Refresh()
    {
        for(EnumCoinTypes type : EnumCoinTypes.values())
        {
            Refresh(type);
        }
    }
    public void Refresh(EnumCoinTypes type)
    {
        String targetUrl = requestURL + CoinTypeToToken(type);

        try {
            URL                 BithumbAPIUrl = new URL(targetUrl);
            InputStreamReader   stream        = new InputStreamReader(BithumbAPIUrl.openConnection().getInputStream(), "UTF-8");

            JSONObject          jsonPrivObject;
            JSONObject          jsonNextObject;

            jsonPrivObject = (JSONObject) jsonParser.parse(stream);
            jsonNextObject = (JSONObject) jsonPrivObject.get("data");

            Infomation[type.ordinal()].MaxPrice = Integer.parseInt(jsonNextObject.get(tokenMaxPrice).toString());
            Infomation[type.ordinal()].MinPrice = Integer.parseInt(jsonNextObject.get(tokenMinPrice).toString());
            Infomation[type.ordinal()].AvgPrice = Integer.parseInt(jsonNextObject.get(tokenAvgPrice).toString());

            stream.close();
        } catch(Exception e) {

        }
    }
    public int getMaxPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].MaxPrice;
    }
    public int getMinPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].MinPrice;
    }
    public int getAvgPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].AvgPrice;
    }
}