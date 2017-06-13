package Sites;

import Exception.ExceptionUnsupportedCoinType;

import Base.IBitcoinSiteApi;
import Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BitStampApi extends IBitcoinSiteApi
{
    private final String requestURL = "https://www.bitstamp.net/api/v2/ticker/";

    private final String tokenMaxPrice = "high";
    private final String tokenMinPrice = "low";
    private final String tokenAvgPrice = "vwap";
    private final String tokenFirstPrice = "open";
    private final String tokenLastPrice = "last";
    private final String tokenSellPrice = "ask";
    private final String tokenBuyPrice = "bid";

    JSONParser JsonParser;

    private String CoinTypeToToken(EnumCoinTypes type) throws ExceptionUnsupportedCoinType
    {
        switch(type)
        {
        case Bitcoin            : return "btcusd";
        case Ripple             : return "xrpusd";
        default : throw new ExceptionUnsupportedCoinType("Unsupported Coin Type!");
        }
    }

    public BitStampApi()
    {
        JsonParser     = new JSONParser();
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
        final String targetURL = requestURL + CoinTypeToToken(type);

        try {
            URL                 BitStampAPIUrl = new URL(targetURL);
            InputStreamReader   stream         = new InputStreamReader(BitStampAPIUrl.openConnection().getInputStream(), "UTF-8");

            JSONObject          jsonObject     = (JSONObject) JsonParser.parse(stream);

            final int coinValue = type.ordinal();

            cachedInfo[coinValue].MaxPrice = Integer.parseInt(jsonObject.get(tokenMaxPrice).toString());
            cachedInfo[coinValue].MinPrice = Integer.parseInt(jsonObject.get(tokenMinPrice).toString());
            cachedInfo[coinValue].AvgPrice = Integer.parseInt(jsonObject.get(tokenAvgPrice).toString());
            cachedInfo[coinValue].FirstPrice = Integer.parseInt(jsonObject.get(tokenFirstPrice).toString());
            cachedInfo[coinValue].LastPrice = Integer.parseInt(jsonObject.get(tokenLastPrice).toString());
            cachedInfo[coinValue].SellPrice = Integer.parseInt(jsonObject.get(tokenSellPrice).toString());
            cachedInfo[coinValue].BuyPrice = Integer.parseInt(jsonObject.get(tokenBuyPrice).toString());

            stream.close();
        } catch(Exception e) {

        }
    }
}