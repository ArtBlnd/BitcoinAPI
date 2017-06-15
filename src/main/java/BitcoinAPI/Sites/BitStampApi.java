package BitcoinAPI.Sites;

import BitcoinAPI.Exception.ExceptionUnsupportedCoinType;

import BitcoinAPI.Base.IBitcoinSiteApiUS;
import BitcoinAPI.Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BitStampApi extends IBitcoinSiteApiUS
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

    public EnumCoinTypes[] getAvailableCoinTypes()
    {
        return new EnumCoinTypes[]
        {
            EnumCoinTypes.Bitcoin,
            EnumCoinTypes.Ripple
        };
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

            super.get(type).MaxPrice = Double.parseDouble((String)jsonObject.get(tokenMaxPrice));
            super.get(type).MinPrice = Double.parseDouble((String)jsonObject.get(tokenMinPrice));
            super.get(type).AvgPrice = Double.parseDouble((String)jsonObject.get(tokenAvgPrice));
            super.get(type).FirstPrice = Double.parseDouble((String)jsonObject.get(tokenFirstPrice));
            super.get(type).LastPrice = Double.parseDouble((String)jsonObject.get(tokenLastPrice));
            super.get(type).SellPrice = Double.parseDouble((String)jsonObject.get(tokenSellPrice));
            super.get(type).BuyPrice = Double.parseDouble((String)jsonObject.get(tokenBuyPrice));

            stream.close();
        } catch(Exception e) {

        }
    }
}