package BitcoinAPI.Base;

import BitcoinAPI.Base.IBitcoinSiteApi;
import BitcoinAPI.Exception.*;

public abstract class IBitcoinSiteApiCN extends IBitcoinSiteApi
{
    private EnumReigonType currentReigon = EnumReigonType.KRW;

    public void setReigon(EnumReigonType reigon)
    {
        
        currentReigon = reigon;
    }

    private double multiplyAsReigon(EnumReigonType reigon, double price)
    {
        try
        {
            switch(reigon)
            {
            case KRW: return BitCoinApiUtils.CnyToKrw(price);
            case USD: return BitCoinApiUtils.CnyToUsd(price);
            case CNY: return price;
            }
        } catch(Exception e) {

        }
        return 0.0;
    }

    // Get max price in 24 hours.
    // 24시간 이내의 최고가를 얻어냅니다.
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getMaxPrice(type));
    }

    // Get min price in 24 hours.
    // 24시간 이내의 최소가를 얻어냅니다.
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getMinPrice(type));
    }

    // Get avg price in 24 hours.
    // 24시간 이내의 평균가를 얻어냅니다.
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getAvgPrice(type));
    }

    // Get first price in 24 hours.
    // 24시간 이내의 첫번째 가격을 가져옵니다.
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getFirstPrice(type));
    }

    // Get last price in 24 hours.
    // 24시간 이내의 마지막 가격을 가져옵니다.
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getLastPrice(type));
    }

    // Get cheapest sell price from site(that you can buy).
    // 가장 싼 가격의 비트코인가를 살수있는(사이트에서 파는.)  가격을 가져옵니다.
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getSellPrice(type));
    }

    //  Get cheapest buy price from site(that you can sell).
    // 가장 싼 비싼의 비트코인가를 팔수있는(사이트에서 사는.) 가격을 가져옵니다.
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getBuyPrice(type));
    }
}