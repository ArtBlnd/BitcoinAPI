package BitcoinAPI.Base;

import BitcoinAPI.Exception.*;

public interface IBitcoinApiBase
{
    public CoinInfo getRatio(IBitcoinApiBase target, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType;
    public CoinInfo getRatioOfCoin(EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType;
    public CoinInfo getRatioAsCoin(IBitcoinApiBase target, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType;

    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType;

    public void setReigon(EnumReigonType region);
    public void Refresh(EnumCoinTypes type) throws Exception;
    public void Refresh() throws Exception;

    public EnumCoinTypes[] getAvailableCoinTypes();
}