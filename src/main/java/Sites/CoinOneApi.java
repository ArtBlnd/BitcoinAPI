package Sites;

import Exception.ExceptionUnsupportedCoinType;

import Base.CoinInfo;
import Base.IBitcoinSiteApi;
import Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CoinOneApi implements IBitcoinSiteApi
{
    private final String tokenMinPrice = "low";
    private final String tokenMaxPrice = "high";
    private final String tokenFirstPrice = "first";
    private final String tokenLastPrice = "last";

    private final String requestURL = "https://api.coinone.co.kr/ticker/";

    JSONParser jsonParser;
    CoinInfo[] Infomation;

    public CoinOneApi()
    {
        jsonParser = new JSONParser();
        Infomation = new CoinInfo[6];
    }

    private String CoinTypeToToken(EnumCoinTypes type) throws ExceptionUnsupportedCoinType
    {
        switch(type)
        {
        case Bitcoin            : return "btc";
        case Etherium           : return "eth";
        case EtheriumClassic    : return "etc";
        case Ripple             : return "xrp";
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

            JSONObject          jsonObject;

            jsonObject = (JSONObject) jsonParser.parse(stream);

            Infomation[type.ordinal()].MaxPrice = Integer.parseInt(jsonObject.get(tokenMaxPrice).toString());
            Infomation[type.ordinal()].MinPrice = Integer.parseInt(jsonObject.get(tokenMinPrice).toString());
            Infomation[type.ordinal()].FirstPrice = Integer.parseInt(jsonObject.get(tokenFirstPrice).toString());
            Infomation[type.ordinal()].LastPrice = Integer.parseInt(jsonObject.get(tokenLastPrice).toString());

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
}