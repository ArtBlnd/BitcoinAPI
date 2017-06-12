package Base;

public interface IBitcoinSiteApi
{
    public void Refresh(EnumCoinTypes type);
    public void Refresh();
    
    public int getMaxPrice(EnumCoinTypes type);
    public int getMinPrice(EnumCoinTypes type);
    public int getAvgPrice(EnumCoinTypes type);
}