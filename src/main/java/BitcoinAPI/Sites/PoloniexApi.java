package BitcoinAPI.Sites;

import BitcoinAPI.Base.IBitcoinSiteApiUS;
import BitcoinAPI.Base.EnumCoinTypes;

public class PoloniexApi extends IBitcoinSiteApiUS
{
    public EnumCoinTypes[] getAvailableCoinTypes()
    {
        return new EnumCoinTypes[]
        {
            
        };
    }
    public void Refresh()
    {
    }
    public void Refresh(EnumCoinTypes type)
    {
        
    }
}