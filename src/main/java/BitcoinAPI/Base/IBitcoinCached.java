package BitcoinAPI.Base;

import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;

public class IBitcoinCached
{
    protected CoinInfo[] cachedInfo;
    static final int nCoinTypes = 6;

    public IBitcoinCached()
    {
        cachedInfo = new CoinInfo[nCoinTypes];
        
        for(int i = 0; i < nCoinTypes; ++i)
        {
            cachedInfo[i] = new CoinInfo();
        }
    }

    public CoinInfo getRatio(IBitcoinCached target, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        info.MaxPrice = target.getMaxPrice(coinType) / getMaxPrice(coinType);
        info.MinPrice = target.getMinPrice(coinType) / getMinPrice(coinType);
        info.AvgPrice = target.getAvgPrice(coinType) / getAvgPrice(coinType);
        info.FirstPrice = target.getFirstPrice(coinType) / getFirstPrice(coinType);
        info.LastPrice = target.getLastPrice(coinType) / getLastPrice(coinType);
        info.SellPrice = target.getSellPrice(coinType) / getSellPrice(coinType);
        info.BuyPrice = target.getBuyPrice(coinType) / getBuyPrice(coinType);

        return info;
    }

    public CoinInfo getRatioOfCoin(EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        info.MaxPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));
        info.MinPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));
        info.AvgPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));
        info.FirstPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));
        info.LastPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));
        info.SellPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));
        info.BuyPrice = (getMaxPrice(targetCoin) / getMaxPrice(baseCoin));

        return info;
    }

    public CoinInfo getRatioAsCoin(IBitcoinCached target, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info_base   = getRatioOfCoin(baseCoin, targetCoin);
        CoinInfo info_target = target.getRatioOfCoin(baseCoin, targetCoin);

        info_base.MaxPrice = info_base.MaxPrice / info_target.MaxPrice;
        info_base.MinPrice = info_base.MinPrice / info_target.MinPrice;
        info_base.AvgPrice = info_base.AvgPrice / info_target.AvgPrice;
        info_base.FirstPrice = info_base.FirstPrice / info_target.FirstPrice;
        info_base.LastPrice = info_base.LastPrice / info_target.LastPrice;
        info_base.SellPrice = info_base.SellPrice / info_target.SellPrice;
        info_base.BuyPrice = info_base.BuyPrice / info_target.BuyPrice;

        return info_base;
    }
    
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