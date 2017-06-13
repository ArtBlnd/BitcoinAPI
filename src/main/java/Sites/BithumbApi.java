package Sites;

import Exception.ExceptionUnsupportedCoinType;

import Base.IBitcoinSiteApi;
import Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BithumbApi extends IBitcoinSiteApi
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

    public BithumbApi()
    {
        jsonParser = new JSONParser();
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

            JSONObject          jsonObject    = (JSONObject)((JSONObject)jsonParser.parse(stream)).get("data");

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