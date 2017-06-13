package Base;

import Exception.ExceptionUnsupportedCoinType;
import Exception.ExceptionUnsupportedPriceType;

public interface IBitcoinSiteApi
{
    public void Refresh(EnumCoinTypes type) throws ExceptionUnsupportedCoinType;
    public void Refresh();
    
    // Get max price in 24 hours
    // 24시간 이내의 최고가를 얻어냅니다.
    public int getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get min price in 24 hours.
    // 24시간 이내의 최소가를 얻어냅니다.
    public int getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get avg price in 24 hours.
    // 24시간 이내의 평균가를 얻어냅니다.
    public int getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get first price in 24 hours.
    // 24시간에서 첫번째 가격을 가져옵니다.
    public int getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get last price in 24 hours.
    // 24시간에서 마지막 가격을 가져옵니다.
    public int getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get cheapest sell price from site(that you can buy).
    // 가장 싼 가격의 비트코인가를 살수있는(사이트에서 파는.)  가격을 가져옵니다.
    public int getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    //  Get cheapest buy price from site(that you can sell).
    // 가장 싼 비싼의 비트코인가를 팔수있는(사이트에서 사는.) 가격을 가져옵니다.
    public int getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
}