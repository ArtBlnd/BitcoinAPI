package BitcoinAPI.Base;

public abstract class IBitcoinSiteApi extends IBitcoinCached
{
    public abstract void setReigon(EnumReigonType region);
    public abstract void Refresh(EnumCoinTypes type) throws Exception;
    public abstract void Refresh() throws Exception;

    public abstract EnumCoinTypes[] getAvailableCoinTypes();
}