package BitcoinAPI;

import BitcoinAPI.Sites.*;
import BitcoinAPI.Base.*;
import BitcoinAPI.Exception.*;

import java.util.ArrayList;

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
    public double getMaxPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getMaxPrice(type);
    }

    // Get min price in 24 hours from target site.
    // 24시간 이내의 최소가를 사이트로부터 얻어냅니다.
    public double getMinPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getMinPrice(type);
    }

    // Get avg price in 24 hours from target site.
    // 24시간 이내의 평균가를 사이트로부터 얻어냅니다.
    public double getAvgPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getAvgPrice(type);
    }

    // Get first price in 24 hours from target site.
    // 24시간 이내의 첫번째 가격을 사이트로부터 가져옵니다.
    public double getFirstPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getFirstPrice(type);
    }

    // Get last price in 24 hours from target site.
    // 24시간 이내의 마지막 가격을 사이트로부터 가져옵니다.
    public double getLastPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getLastPrice(type);
    }

    // Get cheapest sell price from target site(that you can buy).
    // 가장 싼 가격의 비트코인가를 살수있는(사이트에서 파는.) 가격을 사이트로부터 가져옵니다.
    public double getSellPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[site.ordinal()].getSellPrice(type);
    }

    //  Get cheapest buy price from target site(that you can sell).
    // 가장 싼 비싼의 비트코인가를 팔수있는(사이트에서 사는.) 가격을 사이트로부터 가져옵니다.
    public double getBuyPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
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

    CoinInfo getRatioAs(EnumSiteTypes baseSite, EnumSiteTypes targetSite, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[baseSite.ordinal()].getRatio(SiteApis[targetSite.ordinal()], coinType);
    }

    CoinInfo getRatioAsCoin(EnumSiteTypes baseSite, EnumSiteTypes targetSite, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        return SiteApis[baseSite.ordinal()].getRatioAsCoin(SiteApis[targetSite.ordinal()], baseCoin, targetCoin);
    }

    EnumCoinTypes[] getAvailableCoinTypes(EnumSiteTypes site)
    {
        return SiteApis[site.ordinal()].getAvailableCoinTypes();
    }

    EnumCoinTypes[] getAvailableCoinTypes(EnumSiteTypes site_1, EnumSiteTypes site_2)
    {
        EnumCoinTypes[] availableTypes;

        ArrayList<EnumCoinTypes> list = new ArrayList<EnumCoinTypes>();

        IBitcoinSiteApi site_api_1 = SiteApis[site_1.ordinal()];
        IBitcoinSiteApi site_api_2 = SiteApis[site_2.ordinal()];

        for(int i = 0; i < site_api_1.getAvailableCoinTypes().length; ++i)
        {
            for(int j = 0; j < site_api_2.getAvailableCoinTypes().length; ++j)
            {
                if( site_api_1.getAvailableCoinTypes()[i] != site_api_2.getAvailableCoinTypes()[j] )
                {
                    continue;
                }
                list.add(site_api_1.getAvailableCoinTypes()[i]);
            }
        }

        availableTypes = new EnumCoinTypes[list.size()];

        return list.toArray(availableTypes);
    }
};