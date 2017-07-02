package BitcoinAPI.Sites;

import BitcoinAPI.Base.IBitcoinApiBase;
import BitcoinAPI.Base.EnumCoinTypes;
import BitcoinAPI.Base.EnumReigonType;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import BitcoinAPI.Base.CoinInfo;
import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;

public class BittrexApi implements IBitcoinApiBase
{
    private final String targetURL = "https://bittrex.com/api/v1.1/public/getmarketsummary?market=";
    HashMap<EnumCoinTypes, CoinInfo> cachedInfo;

    JSONParser jsonParser;

    private final String tokenMaxPrice = "High";
    private final String tokenMinPrice = "Low";
    private final String tokenSellPrice = "Ask";
    private final String tokenBuyPrice = "Bid";

    public BittrexApi()
    {
        jsonParser = new JSONParser();
    }

    private String CoinTypeToToken(EnumCoinTypes type)
    {
        String token = new String();

        token += "BTC-";

        switch(type)
        {
            case Bitcoin: token += "BTC"; break;
            case Etherium: token += "ETH"; break;
            case Dash: token += "DASH"; break;
            case LightCoin: token += "LTC"; break;
            case Ripple: token += "XRP"; break;
            case EtheriumClassic: token += "ETC"; break;
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

    Double ParseAsDouble(JSONObject jsonObject, String token)
    {
        return Double.parseDouble((String)jsonObject.get(token));
    }

    public void Refresh()
    {
        cachedInfo.clear();

        try{
            for(EnumCoinTypes cointype : getAvailableCoinTypes())
            {
                URL               reqestURL = new URL(targetURL + CoinTypeToToken(cointype));
                InputStreamReader stream = new InputStreamReader(reqestURL.openConnection().getInputStream(), "UTF-8");
                
                JSONObject        jsonObject = (JSONObject)((JSONArray)((JSONObject) jsonParser.parse(stream)).get("result")).get(0);

                CoinInfo newCoinInfo  = new CoinInfo();

                newCoinInfo.MaxPrice = ParseAsDouble(jsonObject, tokenMaxPrice);
                newCoinInfo.MinPrice = ParseAsDouble(jsonObject, tokenMinPrice);
                newCoinInfo.SellPrice = ParseAsDouble(jsonObject, tokenBuyPrice);
                newCoinInfo.BuyPrice = ParseAsDouble(jsonObject, tokenSellPrice);

                cachedInfo.put(cointype, newCoinInfo);
            }

        } catch(Exception e) {
            Refresh();
        }
    }
    public void Refresh(EnumCoinTypes type)
    {
    }
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
        CoinInfo targetInfo = target.getRatioOfCoin(EnumCoinTypes.Bitcoin, coinType);
        CoinInfo baseInfo = cachedInfo.get(coinType);

        info.AvgPrice = targetInfo.AvgPrice / baseInfo.AvgPrice;
        info.MaxPrice = targetInfo.MaxPrice / baseInfo.MaxPrice;
        info.MinPrice = targetInfo.MinPrice / baseInfo.MinPrice;
        info.SellPrice = targetInfo.SellPrice / baseInfo.SellPrice;
        info.BuyPrice = targetInfo.BuyPrice / baseInfo.BuyPrice;
        info.FirstPrice = targetInfo.FirstPrice / baseInfo.FirstPrice;
        info.LastPrice = targetInfo.LastPrice / baseInfo.LastPrice;

        return info;
    }
    public CoinInfo getRatioOfCoin(EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        if(baseCoin == EnumCoinTypes.Bitcoin)
        {
            info = cachedInfo.get(targetCoin);
        }
        else
        {
            CoinInfo targetInfo = cachedInfo.get(targetCoin);
            CoinInfo baseInfo = cachedInfo.get(baseCoin);

            info.AvgPrice = targetInfo.AvgPrice / baseInfo.AvgPrice;
            info.MaxPrice = targetInfo.MaxPrice / baseInfo.MaxPrice;
            info.MinPrice = targetInfo.MinPrice / baseInfo.MinPrice;
            info.SellPrice = targetInfo.SellPrice / baseInfo.SellPrice;
            info.BuyPrice = targetInfo.BuyPrice / baseInfo.BuyPrice;
            info.FirstPrice = targetInfo.FirstPrice / baseInfo.FirstPrice;
            info.LastPrice = targetInfo.LastPrice / baseInfo.LastPrice;
        }

        return info;
    }
    public CoinInfo getRatioAsCoin(IBitcoinApiBase target, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();
        
        CoinInfo targetInfo = target.getRatioOfCoin(baseCoin, targetCoin);
        CoinInfo baseInfo = this.getRatioOfCoin(baseCoin, targetCoin);

        info.AvgPrice = targetInfo.AvgPrice / baseInfo.AvgPrice;
        info.MaxPrice = targetInfo.MaxPrice / baseInfo.MaxPrice;
        info.MinPrice = targetInfo.MinPrice / baseInfo.MinPrice;
        info.SellPrice = targetInfo.SellPrice / baseInfo.SellPrice;
        info.BuyPrice = targetInfo.BuyPrice / baseInfo.BuyPrice;
        info.FirstPrice = targetInfo.FirstPrice / baseInfo.FirstPrice;
        info.LastPrice = targetInfo.LastPrice / baseInfo.LastPrice;

        return info;
    }
}