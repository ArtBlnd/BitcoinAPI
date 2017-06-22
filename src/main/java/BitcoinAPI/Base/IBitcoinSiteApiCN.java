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
    // 24�ð� �̳��� �ְ��� �����ϴ�.
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getMaxPrice(type));
    }

    // Get min price in 24 hours.
    // 24�ð� �̳��� �ּҰ��� �����ϴ�.
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getMinPrice(type));
    }

    // Get avg price in 24 hours.
    // 24�ð� �̳��� ��հ��� �����ϴ�.
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getAvgPrice(type));
    }

    // Get first price in 24 hours.
    // 24�ð� �̳��� ù��° ������ �����ɴϴ�.
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getFirstPrice(type));
    }

    // Get last price in 24 hours.
    // 24�ð� �̳��� ������ ������ �����ɴϴ�.
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getLastPrice(type));
    }

    // Get cheapest sell price from site(that you can buy).
    // ���� �� ������ ��Ʈ���ΰ��� ����ִ�(����Ʈ���� �Ĵ�.)  ������ �����ɴϴ�.
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getSellPrice(type));
    }

    //  Get cheapest buy price from site(that you can sell).
    // ���� �� ����� ��Ʈ���ΰ��� �ȼ��ִ�(����Ʈ���� ���.) ������ �����ɴϴ�.
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return multiplyAsReigon(currentReigon, super.getBuyPrice(type));
    }
}