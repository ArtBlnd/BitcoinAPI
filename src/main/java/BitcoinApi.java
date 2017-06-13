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

    // Get max price in 24 hours from target site.
    // 24시간 이내의 최고가를 사이트로부터 얻어냅니다.
    public int getMaxPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getMaxPrice(type);
    }

    // Get min price in 24 hours from target site.
    // 24시간 이내의 최소가를 사이트로부터 얻어냅니다.
    public int getMinPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getMinPrice(type);
    }

    // Get avg price in 24 hours from target site.
    // 24시간 이내의 평균가를 사이트로부터 얻어냅니다.
    public int getAvgPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getAvgPrice(type);
    }

    // Get first price in 24 hours from target site.
    // 24시간 이내의 첫번째 가격을 사이트로부터 가져옵니다.
    public int getFirstPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getFirstPrice(type);
    }

    // Get last price in 24 hours from target site.
    // 24시간 이내의 마지막 가격을 사이트로부터 가져옵니다.
    public int getLastPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getLastPrice(type);
    }

    // Get cheapest sell price from target site(that you can buy).
    // 가장 싼 가격의 비트코인가를 살수있는(사이트에서 파는.) 가격을 사이트로부터 가져옵니다.
    public int getSellPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getSellPrice(type);
    }

    //  Get cheapest buy price from target site(that you can sell).
    // 가장 싼 비싼의 비트코인가를 팔수있는(사이트에서 사는.) 가격을 사이트로부터 가져옵니다.
    public int getBuyPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getBuyPrice(type);
    }
    
    // Refresh all site infomation.
    // 모든 사이트 정보를 갱신합니다.
    public void Refresh() throws Exception
    {
        for(int indexSite = 0; indexSite < SiteTypeAvailable; ++indexSite)
        {
            SiteApis[indexSite].Refresh();
        }
    }

    // Refresh target site's infomation.
    public void Refresh(EnumSiteTypes site) throws Exception
    {
        SiteApis[site.ordinal()].Refresh();
    }

    // Refresh target coin type's infomation in all sites.
    public void Refresh(EnumCoinTypes type) throws Exception
    {
        for(int indexSite = 0; indexSite < SiteTypeAvailable; ++indexSite)
        {
            SiteApis[indexSite].Refresh(type);
        }
    }

    // Refresh infomation target coin type in target site.
    public void Refresh(EnumSiteTypes site, EnumCoinTypes type) throws Exception
    {
        SiteApis[site.ordinal()].Refresh(type);
    }
};