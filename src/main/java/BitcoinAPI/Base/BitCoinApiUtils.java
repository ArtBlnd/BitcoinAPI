package BitcoinAPI.Base;

import BitcoinAPI.Base.EnumReigonType;
import BitcoinAPI.Base.XchgRateAPI.YahooExchangeAPI;

public class BitCoinApiUtils
{
    private static YahooExchangeAPI Instance_ExchangeAPI;

    private static YahooExchangeAPI getInstance_ExchangeAPI()
    {
        if(Instance_ExchangeAPI == null)
        {
            Instance_ExchangeAPI = new YahooExchangeAPI();
        }

        return Instance_ExchangeAPI;
    }

    public static double UsdToKrw(double usdValue) throws Exception
    {
        return getInstance_ExchangeAPI().getRatioAs(EnumReigonType.USD, EnumReigonType.KRW) * usdValue;
    }

    public static double UsdToCny(double usdValue) throws Exception
    {
        return getInstance_ExchangeAPI().getRatioAs(EnumReigonType.USD, EnumReigonType.CNY) * usdValue;
    }

    public static double KrwToUsd(double krwValue) throws Exception
    {
        return getInstance_ExchangeAPI().getRatioAs(EnumReigonType.KRW, EnumReigonType.USD) * krwValue;
    }

    public static double KrwToCny(double krwValue) throws Exception
    {
        return getInstance_ExchangeAPI().getRatioAs(EnumReigonType.KRW, EnumReigonType.CNY) * krwValue;
    }

    public static double CnyToUsd(double krwValue) throws Exception
    {
        return getInstance_ExchangeAPI().getRatioAs(EnumReigonType.CNY, EnumReigonType.USD) * krwValue;
    }
    
    public static double CnyToKrw(double krwValue) throws Exception
    {
        return getInstance_ExchangeAPI().getRatioAs(EnumReigonType.CNY, EnumReigonType.KRW) * krwValue;
    }
};