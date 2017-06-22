package BitcoinAPI.Base.XchgRateAPI;

import java.util.HashMap;
import java.io.InputStreamReader;
import java.net.*;

import BitcoinAPI.Base.EnumReigonType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class YahooExchangeAPI extends HashMap<EnumReigonType, Double>
{
    private final String targetStartURL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%3D\"";
    private final String targetEndURL = "\"&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    JSONParser jsonParser;

    private EnumReigonType currentBase;

    public YahooExchangeAPI()
    {
        jsonParser = new JSONParser();

        super.put(EnumReigonType.CNY, 0.0);
        super.put(EnumReigonType.KRW, 0.0);
        super.put(EnumReigonType.USD, 0.0);
    }

    private String ExchangeTypeToToken(EnumReigonType type)
    {
        switch(type)
        {
            case USD: return "USD";
            case KRW: return "KRW";
            case CNY: return "CNY";
        }
        return "";
    }

    // 1의 base의 기준으로 target의 비율을 구합니다.
    // 예시 > 1Dolar (base) : 1240WON (target) returns 1240.
    public double getRatioAs(EnumReigonType base, EnumReigonType target) throws Exception
    {
        final String requestURL = targetStartURL + ExchangeTypeToToken(base) + ExchangeTypeToToken(target) + targetEndURL;

        URL                 requestAPIUrl       = new URL(requestURL);
        InputStreamReader   stream              = new InputStreamReader(requestAPIUrl.openConnection().getInputStream(), "UTF-8");
        JSONObject          jsonObject          = (JSONObject) jsonParser.parse(stream);
        
        return Double.parseDouble((String)((JSONObject)((JSONObject)((JSONObject)jsonObject.get("query")).get("results")).get("rate")).get("Rate"));
    }
}