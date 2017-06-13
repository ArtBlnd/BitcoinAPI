package Sites.XchgRateAPI;

import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class YahooExchangeAPI
{
    private final String targetStartURL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%3D\"";
    private final String targetEndURL = "\"&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    JSONParser jsonParser;

    public YahooExchangeAPI()
    {
        jsonParser = new JSONParser();  
    }

    private String ExchangeTypeToToken(EnumExchangeType type)
    {
        switch(type)
        {
            case USD: return "USD";
            case KRW: return "KRW";
        }
        return "";
    }

    // 1의 target_1의 기준으로 target_2의 비율을 구합니다.
    // 예시 > 1Dolar (target_1) : 1240WON (target_2) returns 1240.
    public double Ratio(EnumExchangeType target_1, EnumExchangeType target_2) throws Exception
    {
        final String requestURL = targetStartURL + ExchangeTypeToToken(target_1) + ExchangeTypeToToken(target_2) + targetEndURL;


        URL requestAPIUrl           = new URL(requestURL);
        InputStreamReader stream    = new InputStreamReader(requestAPIUrl.openConnection().getInputStream(), "UTF-8");

        return 0.0f;
    }
}