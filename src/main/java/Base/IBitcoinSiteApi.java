package Base;

import Exception.ExceptionUnsupportedCoinType;

public interface IBitcoinSiteApi
{
    public void Refresh(EnumCoinTypes type) throws ExceptionUnsupportedCoinType;
    public void Refresh();
    
    public int getMaxPrice(EnumCoinTypes type);
    public int getMinPrice(EnumCoinTypes type);
    public int getAvgPrice(EnumCoinTypes type);
    public int getFirstPrice(EnumCoinTypes type);
    public int getLastPrice(EnumCoinTypes type);
}