package BitcoinAPI.Sites;

import BitcoinAPI.Exception.ExceptionUnsupportedCoinType;

import BitcoinAPI.Base.IBitcoinSiteApiKR;
import BitcoinAPI.Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BithumbApi extends IBitcoinSiteApiKR
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

    public EnumCoinTypes[] getAvailableCoinTypes()
    {
        return new EnumCoinTypes[] 
        { 
            EnumCoinTypes.Bitcoin, 
            EnumCoinTypes.Etherium, 
            EnumCoinTypes.Dash, 
            EnumCoinTypes.LightCoin, 
            EnumCoinTypes.EtheriumClassic, 
            EnumCoinTypes.Ripple
        };
    }

    public void Refresh() throws Exception
    {
        for(EnumCoinTypes type : EnumCoinTypes.values())
        {
            Refresh(type);
        }
    }
    public void Refresh(EnumCoinTypes type) throws Exception
    {
        String targetUrl = requestURL + CoinTypeToToken(type);

        URL                 BithumbAPIUrl = new URL(targetUrl);
        InputStreamReader   stream        = new InputStreamReader(BithumbAPIUrl.openConnection().getInputStream(), "UTF-8");
        JSONObject          jsonObject    = (JSONObject)((JSONObject)jsonParser.parse(stream)).get("data");

        super.get(type).MaxPrice = Double.parseDouble((String)jsonObject.get(tokenMaxPrice));
        super.get(type).MinPrice = Double.parseDouble((String)jsonObject.get(tokenMinPrice));
        super.get(type).AvgPrice = Double.parseDouble((String)jsonObject.get(tokenAvgPrice));
        super.get(type).FirstPrice = Double.parseDouble((String)jsonObject.get(tokenFirstPrice));
        super.get(type).LastPrice = Double.parseDouble((String)jsonObject.get(tokenLastPrice));
        super.get(type).SellPrice = Double.parseDouble((String)jsonObject.get(tokenSellPrice));
        super.get(type).BuyPrice = Double.parseDouble((String)jsonObject.get(tokenBuyPrice));

        stream.close();
    }
}