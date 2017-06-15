package BitcoinAPI.Base;

import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;
import java.util.HashMap;

public abstract class IBitcoinCached extends HashMap<EnumCoinTypes, CoinInfo> implements IBitcoinApiBase
{
    public IBitcoinCached()
    {
        for (EnumCoinTypes type : EnumCoinTypes.values())
        {
            super.put(type, new CoinInfo());
        }
    }

    public CoinInfo getRatio(IBitcoinApiBase target, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType
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

    public CoinInfo getRatioAsCoin(IBitcoinApiBase target, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
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
    // 24�ð� �̳��� �ְ����� �����ϴ�.
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).MaxPrice;
    }

    // Get min price in 24 hours.
    // 24�ð� �̳��� �ּҰ��� �����ϴ�.
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).MinPrice;
    }

    // Get avg price in 24 hours.
    // 24�ð� �̳��� ��հ��� �����ϴ�.
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).AvgPrice;
    }

    // Get first price in 24 hours.
    // 24�ð� �̳��� ù��° ������ �����ɴϴ�.
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).FirstPrice;
    }

    // Get last price in 24 hours.
    // 24�ð� �̳��� ������ ������ �����ɴϴ�.
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).LastPrice;
    }

    // Get cheapest sell price from site(that you can buy).
    // ���� �� ������ ��Ʈ���ΰ��� ����ִ�(����Ʈ���� �Ĵ�.)  ������ �����ɴϴ�.
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).SellPrice;
    }

    //  Get cheapest buy price from site(that you can sell).
    // ���� �� ����� ��Ʈ���ΰ��� �ȼ��ִ�(����Ʈ���� ���.) ������ �����ɴϴ�.
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return super.get(type).BuyPrice;
    }
}