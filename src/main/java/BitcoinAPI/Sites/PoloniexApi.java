package BitcoinAPI.Sites;

import BitcoinAPI.Base.IBitcoinApiBase;
import BitcoinAPI.Base.EnumCoinTypes;
import BitcoinAPI.Base.EnumReigonType;
import BitcoinAPI.Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import BitcoinAPI.Base.CoinInfo;
import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;


// Poloniex only supports bitcoin - bitcoin trade. so we will not support any methods named getXXXXPrice()
// Poloniex는 오직 비트코인 대 비트코인 거래만 지원합니다. 그러므로 저희들은 getXXXXPrice()로 이름지어진 메소드들을 지원하지 않을 것입니다.
public class PoloniexApi implements IBitcoinApiBase
{
    HashMap<EnumCoinTypes, CoinInfo> cachedInfo;

    private final String targetURL = "https://poloniex.com/public?command=returnTicker";
    private JSONParser jsonParser;

    public PoloniexApi()
    {
        jsonParser = new JSONParser();
        cachedInfo = new HashMap<EnumCoinTypes,CoinInfo>();
    }

    private String CoinTypeToToken(EnumCoinTypes type)
    {
        String token = new String();

        token += "BTC_";

        switch(type)
        {
            case Bitcoin: token += "BTC";
            case Etherium: token += "ETH";
            case Dash: token += "DASH";
            case LightCoin: token += "LTC";
            case Ripple: token += "XRP";
            case EtheriumClassic: token += "ETC";
        }

        return token;
    }

    public EnumCoinTypes[] getAvailableCoinTypes()
    {
        return new EnumCoinTypes[]
        {
            EnumCoinTypes.Bitcoin,
            EnumCoinTypes.Etherium,
            EnumCoinTypes.Dash,
            EnumCoinTypes.LightCoin,
            EnumCoinTypes.Ripple,
            EnumCoinTypes.EtheriumClassic
        };
    }

    JSONObject ParseAsObject(String Token, InputStreamReader stream) throws Exception
    {
        return (JSONObject)((JSONObject)jsonParser.parse(stream)).get(Token);
    }

    Double ParseAsDouble(JSONObject jsonObject, String token)
    {
        return Double.parseDouble((String)jsonObject.get(token));
    }

    public void Refresh()
    {
        cachedInfo.clear();

        try {
            URL               reqestURL = new URL(targetURL);
            InputStreamReader stream = new InputStreamReader(reqestURL.openConnection().getInputStream(), "UTF-8");

            for(EnumCoinTypes cointype : getAvailableCoinTypes())
            {
                JSONObject jsonObject = ParseAsObject(CoinTypeToToken(cointype), stream);
                CoinInfo newCoinInfo  = new CoinInfo();

                newCoinInfo.MaxPrice = ParseAsDouble(jsonObject, "high24hr");
                newCoinInfo.MinPrice = ParseAsDouble(jsonObject, "low24hr");
                newCoinInfo.SellPrice = ParseAsDouble(jsonObject, "highestBid");
                newCoinInfo.BuyPrice = ParseAsDouble(jsonObject, "lowestAsk");

                cachedInfo.put(cointype, newCoinInfo);
            }

        } catch(Exception e) {
            Refresh();
        }
    }
    public void Refresh(EnumCoinTypes type)
    {
        
    }

    // NOT SUPPORTING!!
    // 지원하지 않습니다!!
    public void setReigon(EnumReigonType type) { }
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }

    public CoinInfo getRatio(IBitcoinApiBase target, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        return info;
    }
    public CoinInfo getRatioOfCoin(EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        return info;
    }
    public CoinInfo getRatioAsCoin(IBitcoinApiBase target, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        return info;
    }
}