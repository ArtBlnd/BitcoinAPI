package BitcoinAPI.Sites;

import BitcoinAPI.Exception.ExceptionUnsupportedCoinType;
import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;

import BitcoinAPI.Base.CoinInfo;
import BitcoinAPI.Base.IBitcoinSiteApiUS;
import BitcoinAPI.Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BitfineExApi extends IBitcoinSiteApiUS
{
    public void Refresh()
    {
    }
    public void Refresh(EnumCoinTypes type)
    {
        
    }
}