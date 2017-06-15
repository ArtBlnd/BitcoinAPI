package BitcoinAPI;

import BitcoinAPI.Sites.*;
import BitcoinAPI.Base.*;
import BitcoinAPI.Exception.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BitcoinApi
{
    HashMap<EnumSiteTypes, IBitcoinApiBase> Sites;

    public final int CoinTypeAvailable = 6;
    public final int SiteTypeAvailable = 9;

    public BitcoinApi()
    {
        Sites = new HashMap<EnumSiteTypes, IBitcoinApiBase>();

        Sites.put(EnumSiteTypes.Poloniex, new PoloniexApi());
        Sites.put(EnumSiteTypes.Bithumb, new BithumbApi());
        Sites.put(EnumSiteTypes.CoinOne, new CoinOneApi());
        Sites.put(EnumSiteTypes.Yunbi, new YunbiApi());
        Sites.put(EnumSiteTypes.Bittrex, new BittrexApi());
        Sites.put(EnumSiteTypes.BitfineEx, new BitfineExApi());
        Sites.put(EnumSiteTypes.BitStamp, new BitStampApi());
        Sites.put(EnumSiteTypes.OkCoin, new OkCoinApi());
    }

    // Get max price in 24 hours from target site.
    // 24�ð� �̳��� �ְ��� ����Ʈ�κ��� �����ϴ�.
    public double getMaxPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getMaxPrice(type);
    }

    // Get min price in 24 hours from target site.
    // 24�ð� �̳��� �ּҰ��� ����Ʈ�κ��� �����ϴ�.
    public double getMinPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getMinPrice(type);
    }

    // Get avg price in 24 hours from target site.
    // 24�ð� �̳��� ��հ��� ����Ʈ�κ��� �����ϴ�.
    public double getAvgPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getAvgPrice(type);
    }

    // Get first price in 24 hours from target site.
    // 24�ð� �̳��� ù��° ������ ����Ʈ�κ��� �����ɴϴ�.
    public double getFirstPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getFirstPrice(type);
    }

    // Get last price in 24 hours from target site.
    // 24�ð� �̳��� ������ ������ ����Ʈ�κ��� �����ɴϴ�.
    public double getLastPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getLastPrice(type);
    }

    // Get cheapest sell price from target site(that you can buy).
    // ���� �� ������ ��Ʈ���ΰ��� ����ִ�(����Ʈ���� �Ĵ�.) ������ ����Ʈ�κ��� �����ɴϴ�.
    public double getSellPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getSellPrice(type);
    }

    //  Get cheapest buy price from target site(that you can sell).
    // ���� �� ����� ��Ʈ���ΰ��� �ȼ��ִ�(����Ʈ���� ���.) ������ ����Ʈ�κ��� �����ɴϴ�.
    public double getBuyPrice(EnumSiteTypes site, EnumCoinTypes type) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(site).getBuyPrice(type);
    }
    
    public void setReigon(EnumReigonType reigon) throws Exception
    {
        for(EnumSiteTypes site : EnumSiteTypes.values())
        {
            Sites.get(site).setReigon(reigon);
        }
    }

    // Refresh all site infomation.
    // ��� ����Ʈ ������ �����մϴ�.
    public void Refresh() throws Exception
    {
        for(EnumSiteTypes site : EnumSiteTypes.values())
        {
            Sites.get(site).Refresh();
        }
    }

    // Refresh target site's infomation.
    public void Refresh(EnumSiteTypes site) throws Exception
    {
        Sites.get(site).Refresh();
    }

    // Refresh target coin type's infomation in all sites.
    public void Refresh(EnumCoinTypes type) throws Exception
    {
        for(EnumSiteTypes site : EnumSiteTypes.values())
        {
            Sites.get(site).Refresh(type);
        }
    }

    // Refresh infomation target coin type in target site.
    public void Refresh(EnumSiteTypes site, EnumCoinTypes type) throws Exception
    {
        Sites.get(site).Refresh(type);
    }

    CoinInfo getRatioAs(EnumSiteTypes baseSite, EnumSiteTypes targetSite, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(baseSite).getRatio(Sites.get(targetSite), coinType);
    }

    CoinInfo getRatioAsCoin(EnumSiteTypes baseSite, EnumSiteTypes targetSite, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        return Sites.get(baseSite).getRatioAsCoin(Sites.get(targetSite), baseCoin, targetCoin);
    }

    EnumCoinTypes[] getAvailableCoinTypes(EnumSiteTypes site)
    {
        return Sites.get(site).getAvailableCoinTypes();
    }

    EnumCoinTypes[] getAvailableCoinTypes(EnumSiteTypes site_1, EnumSiteTypes site_2)
    {
        EnumCoinTypes[] availableTypes;

        ArrayList<EnumCoinTypes> list = new ArrayList<EnumCoinTypes>();

        IBitcoinApiBase site_api_1 = Sites.get(site_1);
        IBitcoinApiBase site_api_2 = Sites.get(site_2);

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