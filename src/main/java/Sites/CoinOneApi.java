package Sites;

import Exception.ExceptionUnsupportedCoinType;
import Exception.ExceptionUnsupportedPriceType;

import Base.CoinInfo;
import Base.IBitcoinSiteApi;
import Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CoinOneApi implements IBitcoinSiteApi
{
    private final String tokenMinPrice = "low";
    private final String tokenMaxPrice = "high";
    private final String tokenFirstPrice = "first";
    private final String tokenLastPrice = "last";

    private final String tickerRequestURL = "https://api.coinone.co.kr/ticker/";
    private final String orderbookRequestURL = "https://api.coinone.co.kr/orderbook/";

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
        String targetTickerURL    = tickerRequestURL + CoinTypeToToken(type);
        String targetOrderbookURL = orderbookRequestURL + CoinTypeToToken(type);

        try {
            URL                 CoinOneApiTickerURL     = new URL(targetTickerURL);
            InputStreamReader   stream_ticker           = new InputStreamReader(CoinOneApiTickerURL.openConnection().getInputStream(), "UTF-8");
            JSONObject          jsonObject_ticker;

            URL                 CoinOneApiOrderbookURL  = new URL(targetOrderbookURL);
            InputStreamReader   stream_orderbook        = new InputStreamReader(CoinOneApiOrderbookURL.openConnection().getInputStream(), "UTF-8");
            JSONObject          jsonObject_orderbook;
            JSONArray           jsonArray_orderbook_bid;
            JSONArray           jsonArray_orderbook_ask;

            jsonObject_ticker       = (JSONObject) jsonParser.parse(stream_ticker);
            jsonObject_orderbook    = (JSONObject) jsonParser.parse(stream_orderbook);
            jsonArray_orderbook_ask = (JSONArray) jsonObject_orderbook.get("ask");
            jsonArray_orderbook_bid = (JSONArray) jsonObject_orderbook.get("bid");

            Infomation[type.ordinal()].MaxPrice = Integer.parseInt(jsonObject_ticker.get(tokenMaxPrice).toString());
            Infomation[type.ordinal()].MinPrice = Integer.parseInt(jsonObject_ticker.get(tokenMinPrice).toString());
            Infomation[type.ordinal()].FirstPrice = Integer.parseInt(jsonObject_ticker.get(tokenFirstPrice).toString());
            Infomation[type.ordinal()].LastPrice = Integer.parseInt(jsonObject_ticker.get(tokenLastPrice).toString());

            // Parsing Index of JSONArray.size() because last object will return least/most size of bid
            // JSONArray.size()를 index로 파싱하는 이유는. 제일 작거나 제일 큰 값으로 매겨진 값을 얻기 위해서입니다.
            Infomation[type.ordinal()].SellPrice = Integer.parseInt(jsonArray_orderbook_ask.get(jsonArray_orderbook_ask.size()).toString());
            Infomation[type.ordinal()].BuyPrice = Integer.parseInt(jsonArray_orderbook_ask.get(jsonArray_orderbook_bid.size()).toString());

            stream_ticker.close();
            stream_orderbook.close();
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
    public int getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        throw new ExceptionUnsupportedPriceType("Avg Price is not supported in this site!");
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