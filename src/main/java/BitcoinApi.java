import Sites.*;
import Base.*;
import Exception.*;

public class BitcoinApi
{
    private IBitcoinSiteApi[] SiteApis;
    public final int CoinTypeAvailable = 6;
    public final int SiteTypeAvailable = 9;

    public BitcoinApi()
    {
        SiteApis = new IBitcoinSiteApi[SiteTypeAvailable];

        SiteApis[EnumSiteTypes.Poloniex.ordinal()] = new PoloniexApi();
        SiteApis[EnumSiteTypes.Bithumb.ordinal()] = new BithumbApi();
        SiteApis[EnumSiteTypes.CoinOne.ordinal()] = new CoinOneApi();
        SiteApis[EnumSiteTypes.Yunbi.ordinal()] = new YunbiApi();
        SiteApis[EnumSiteTypes.Bittrex.ordinal()] = new BittrexApi();
        SiteApis[EnumSiteTypes.BitfineEx.ordinal()] = new BitfineExApi();
        SiteApis[EnumSiteTypes.BitStamp.ordinal()] = new BitStampApi();
        SiteApis[EnumSiteTypes.OkCoin.ordinal()] = new OkCoinApi();
    }

    public int getMaxPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getMaxPrice(type);
    }

    public int getMinPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getMinPrice(type);
    }

    public int getAvgPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getAvgPrice(type);
    }

    public int getFirstPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getFirstPrice(type);
    }
    public int getLastPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getLastPrice(type);
    }
    public int getSellPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getSellPrice(type);
    }
    public int getBuyPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getBuyPrice(type);
    }

    public void Refresh() throws Exception
    {
        for(int indexSite = 0; indexSite < SiteTypeAvailable; ++indexSite)
        {
            SiteApis[indexSite].Refresh();
        }
    }
    public void Refresh(EnumSiteTypes site) throws Exception
    {
        SiteApis[site.ordinal()].Refresh();
    }
    public void Refresh(EnumCoinTypes type) throws Exception
    {
        for(int indexSite = 0; indexSite < SiteTypeAvailable; ++indexSite)
        {
            SiteApis[indexSite].Refresh(type);
        }
    }
    public void Refresh(EnumSiteTypes site, EnumCoinTypes type) throws Exception
    {
        SiteApis[site.ordinal()].Refresh(type);
    }
};