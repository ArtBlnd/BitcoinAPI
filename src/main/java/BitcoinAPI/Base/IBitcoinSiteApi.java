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
    // 24�ð� �̳��� �ְ��� �����ϴ�.
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].MaxPrice;
    }

    // Get min price in 24 hours.
    // 24�ð� �̳��� �ּҰ��� �����ϴ�.
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].MinPrice;
    }

    // Get avg price in 24 hours.
    // 24�ð� �̳��� ��հ��� �����ϴ�.
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].AvgPrice;
    }

    // Get first price in 24 hours.
    // 24�ð� �̳��� ù��° ������ �����ɴϴ�.
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].FirstPrice;
    }

    // Get last price in 24 hours.
    // 24�ð� �̳��� ������ ������ �����ɴϴ�.
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].LastPrice;
    }

    // Get cheapest sell price from site(that you can buy).
    // ���� �� ������ ��Ʈ���ΰ��� ����ִ�(����Ʈ���� �Ĵ�.)  ������ �����ɴϴ�.
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].SellPrice;
    }

    //  Get cheapest buy price from site(that you can sell).
    // ���� �� ����� ��Ʈ���ΰ��� �ȼ��ִ�(����Ʈ���� ���.) ������ �����ɴϴ�.
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return cachedInfo[type.ordinal()].BuyPrice;
    }
}