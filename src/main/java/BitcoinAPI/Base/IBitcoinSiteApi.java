package BitcoinAPI.Base;

import BitcoinAPI.Exception.ExceptionUnsupportedCoinType;
import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;

public abstract class IBitcoinSiteApi
{
    protected CoinInfo[] cachedInfo;
    static final int nCoinTypes = 6;

    public IBitcoinSiteApi()
    {
        cachedInfo = new CoinInfo[nCoinTypes];
        
        for(int i = 0; i < nCoinTypes; ++i)
        {
            cachedInfo[i] = new CoinInfo();
        }
    }

    public abstract void setReigon(EnumReigonType region);
    public abstract void Refresh(EnumCoinTypes type) throws ExceptionUnsupportedCoinType;
    public abstract void Refresh();
    
    // Get max price in 24 hours.
    // 24시간 이내의 최고가를 얻어냅니다.
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].MaxPrice;
    }

    // Get min price in 24 hours.
    // 24시간 이내의 최소가를 얻어냅니다.
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].MinPrice;
    }

    // Get avg price in 24 hours.
    // 24시간 이내의 평균가를 얻어냅니다.
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].AvgPrice;
    }

    // Get first price in 24 hours.
    // 24시간 이내의 첫번째 가격을 가져옵니다.
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].FirstPrice;
    }

    // Get last price in 24 hours.
    // 24시간 이내의 마지막 가격을 가져옵니다.
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].LastPrice;
    }

    // Get cheapest sell price from site(that you can buy).
    // 가장 싼 가격의 비트코인가를 살수있는(사이트에서 파는.)  가격을 가져옵니다.
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].SellPrice;
    }

    //  Get cheapest buy price from site(that you can sell).
    // 가장 싼 비싼의 비트코인가를 팔수있는(사이트에서 사는.) 가격을 가져옵니다.
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].BuyPrice;
    }
}