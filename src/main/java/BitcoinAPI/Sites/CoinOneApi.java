package BitcoinAPI.Sites;

import BitcoinAPI.Exception.ExceptionUnsupportedCoinType;
import BitcoinAPI.Base.IBitcoinSiteApiKR;
import BitcoinAPI.Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CoinOneApi extends IBitcoinSiteApiKR
{
    private final String tokenMinPrice = "low";
    private final String tokenMaxPrice = "high";
    private final String tokenFirstPrice = "first";
    private final String tokenLastPrice = "last";
    private final String tokenPrice = "price";

    private final String tickerRequestURL = "https://api.coinone.co.kr/ticker/";
    private final String orderbookRequestURL = "https://api.coinone.co.kr/orderbook/";

    JSONParser jsonParser;

    public CoinOneApi()
    {
        jsonParser = new JSONParser();
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
        String targetTickerURL    = tickerRequestURL + CoinTypeToToken(type);
        String targetOrderbookURL = orderbookRequestURL + CoinTypeToToken(type);

        try {
            URL                 CoinOneApiTickerURL     = new URL(targetTickerURL);
            InputStreamReader   stream_ticker           = new InputStreamReader(CoinOneApiTickerURL.openConnection().getInputStream(), "UTF-8");
            JSONObject          jsonObject_ticker;

            URL                 CoinOneApiOrderbookURL  = new URL(targetOrderbookURL);
            InputStreamReader   stream_orderbook        = new InputStreamReader(CoinOneApiOrderbookURL.openConnection().getInputStream(), "UTF-8");
            JSONObject          jsonObject_orderbook;
            JSONObject          jsonObject_orderbook_price_bid;
            JSONObject          jsonObject_orderbook_price_ask;
            JSONArray           jsonArray_orderbook_bid;
            JSONArray           jsonArray_orderbook_ask;

            jsonObject_ticker       = (JSONObject) jsonParser.parse(stream_ticker);

            jsonObject_orderbook    = (JSONObject) jsonParser.parse(stream_orderbook);
            jsonArray_orderbook_ask = (JSONArray) jsonObject_orderbook.get("ask");
            jsonArray_orderbook_bid = (JSONArray) jsonObject_orderbook.get("bid");

            cachedInfo[type.ordinal()].MaxPrice = Double.parseDouble(jsonObject_ticker.get(tokenMaxPrice).toString());
            cachedInfo[type.ordinal()].MinPrice = Double.parseDouble(jsonObject_ticker.get(tokenMinPrice).toString());
            cachedInfo[type.ordinal()].FirstPrice = Double.parseDouble(jsonObject_ticker.get(tokenFirstPrice).toString());
            cachedInfo[type.ordinal()].LastPrice = Double.parseDouble(jsonObject_ticker.get(tokenLastPrice).toString());

            // Parsing Index of JSONArray.size() because last object will return least/most size of bid
            // JSONArray.size()를 index로 파싱하는 이유는. 제일 작거나 제일 큰 값으로 매겨진 값을 얻기 위해서입니다.

            jsonObject_orderbook_price_bid = (JSONObject)jsonArray_orderbook_bid.get(jsonArray_orderbook_ask.size());
            jsonObject_orderbook_price_ask = (JSONObject)jsonArray_orderbook_ask.get(jsonArray_orderbook_ask.size());

            cachedInfo[type.ordinal()].BuyPrice = Double.parseDouble(jsonObject_orderbook_price_bid.get(tokenPrice).toString());
            cachedInfo[type.ordinal()].SellPrice = Double.parseDouble(jsonObject_orderbook_price_ask.get(tokenPrice).toString());
            
            stream_ticker.close();
            stream_orderbook.close();
        } catch(Exception e) {

        }
    }
}