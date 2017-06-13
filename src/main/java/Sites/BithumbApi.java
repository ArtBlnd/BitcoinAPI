package Sites;

import Exception.ExceptionUnsupportedCoinType;

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
    private final String tokenFirstPrice = "opening_price";
    private final String tokenLastPrice = "closing_price";
    private final String tokenSellPrice = "sell_price";
    private final String tokenBuyPrice = "buy_price";

    private final String requestURL = "https://api.bithumb.com/public/ticker/";

    JSONParser jsonParser;
    CoinInfo[] Infomation;

    public BithumbApi()
    {
        jsonParser = new JSONParser();
        Infomation = new CoinInfo[6];
    }

    private String CoinTypeToToken(EnumCoinTypes type) throws ExceptionUnsupportedCoinType
    {
        switch(type)
        {
        case Bitcoin            : return "BTC";
        case Etherium           : return "ETH";
        case Dash               : return "DASH";
        case LightCoin          : return "LTC";
        case EtheriumClassic    : return "ETC";
        case Ripple             : return "XRP";
        default : throw new ExceptionUnsupportedCoinType("Unsupported Coin Type!");
        }
    }

    public void Refresh() 
    {
        for(EnumCoinTypes type : EnumCoinTypes.values())
        {
            try {
                Refresh(type);
            } catch(ExceptionUnsupportedCoinType e) {

            }
        }
    }
    public void Refresh(EnumCoinTypes type) throws ExceptionUnsupportedCoinType
    {
        String targetUrl = requestURL + CoinTypeToToken(type);

        try {
            URL                 BithumbAPIUrl = new URL(targetUrl);
            InputStreamReader   stream        = new InputStreamReader(BithumbAPIUrl.openConnection().getInputStream(), "UTF-8");

            JSONObject          jsonPrivObject;
            JSONObject          jsonNextObject;

            jsonPrivObject = (JSONObject) jsonParser.parse(stream);
            jsonNextObject = (JSONObject) jsonPrivObject.get("data");

            final int coinValue = type.ordinal();

            Infomation[coinValue].MaxPrice = Integer.parseInt(jsonNextObject.get(tokenMaxPrice).toString());
            Infomation[coinValue].MinPrice = Integer.parseInt(jsonNextObject.get(tokenMinPrice).toString());
            Infomation[coinValue].AvgPrice = Integer.parseInt(jsonNextObject.get(tokenAvgPrice).toString());
            Infomation[coinValue].FirstPrice = Integer.parseInt(jsonNextObject.get(tokenFirstPrice).toString());
            Infomation[coinValue].LastPrice = Integer.parseInt(jsonNextObject.get(tokenLastPrice).toString());
            Infomation[coinValue].SellPrice = Integer.parseInt(jsonNextObject.get(tokenSellPrice).toString());
            Infomation[coinValue].BuyPrice = Integer.parseInt(jsonNextObject.get(tokenBuyPrice).toString());

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
    public int getFirstPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].FirstPrice;
    }
    public int getLastPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].LastPrice;
    }
    public int getSellPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].SellPrice;
    }
    public int getBuyPrice(EnumCoinTypes type)
    {
        return Infomation[type.ordinal()].BuyPrice;
    }
}