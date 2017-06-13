package Sites;

import Base.IBitcoinSiteApi;
import Base.EnumCoinTypes;

public class BittrexApi implements IBitcoinSiteApi
{
    public void Refresh()
    {
    }
    public void Refresh(EnumCoinTypes type)
    {
        
    }
    public int getMaxPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getMinPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getAvgPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getFirstPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getLastPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getSellPrice(EnumCoinTypes type)
    {
        return 0;
    }
    public int getBuyPrice(EnumCoinTypes type)
    {
        return 0;
    }
}