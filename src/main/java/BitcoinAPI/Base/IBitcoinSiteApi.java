package BitcoinAPI.Base;

import BitcoinAPI.Exception.ExceptionUnsupportedCoinType;

public abstract class IBitcoinSiteApi extends IBitcoinCached
{
    public abstract void setReigon(EnumReigonType region);
    public abstract void Refresh(EnumCoinTypes type) throws ExceptionUnsupportedCoinType;
    public abstract void Refresh();

    public abstract EnumCoinTypes[] getAvailableCoinTypes();
}