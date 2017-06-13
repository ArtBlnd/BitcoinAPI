package Base;

import Exception.ExceptionUnsupportedCoinType;
import Exception.ExceptionUnsupportedPriceType;

public interface IBitcoinSiteApi
{
    public void Refresh(EnumCoinTypes type) throws ExceptionUnsupportedCoinType;
    public void Refresh();
    
    // Get max price in 24 hours
    // 24�ð� �̳��� �ְ��� �����ϴ�.
    public int getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get min price in 24 hours.
    // 24�ð� �̳��� �ּҰ��� �����ϴ�.
    public int getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get avg price in 24 hours.
    // 24�ð� �̳��� ��հ��� �����ϴ�.
    public int getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get first price in 24 hours.
    // 24�ð����� ù��° ������ �����ɴϴ�.
    public int getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get last price in 24 hours.
    // 24�ð����� ������ ������ �����ɴϴ�.
    public int getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    // Get cheapest sell price from site(that you can buy).
    // ���� �� ������ ��Ʈ���ΰ��� ����ִ�(����Ʈ���� �Ĵ�.)  ������ �����ɴϴ�.
    public int getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    //  Get cheapest buy price from site(that you can sell).
    // ���� �� ����� ��Ʈ���ΰ��� �ȼ��ִ�(����Ʈ���� ���.) ������ �����ɴϴ�.
    public int getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
}