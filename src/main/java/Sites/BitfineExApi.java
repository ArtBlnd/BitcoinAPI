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

public class BitfineExApi extends IBitcoinSiteApi
{
    public void Refresh()
    {
    }
    public void Refresh(EnumCoinTypes type)
    {
        
    }
    public int getMaxPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getMinPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getAvgPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getFirstPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getLastPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getSellPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getBuyPrice(EnumCoinTypes type)
    {
        return 0;
    }
}