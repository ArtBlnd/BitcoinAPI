package BitcoinAPI.Sites;

import BitcoinAPI.Base.IBitcoinSiteApiCN;
import BitcoinAPI.Base.EnumCoinTypes;

public class OkCoinApi extends IBitcoinSiteApiCN
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