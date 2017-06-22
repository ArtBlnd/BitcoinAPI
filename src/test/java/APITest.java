import java.io.Console;

import org.junit.Assert;
import org.junit.Test;

import BitcoinAPI.BitcoinApi;
import BitcoinAPI.Base.EnumCoinTypes;
import BitcoinAPI.Base.EnumReigonType;
import BitcoinAPI.Base.EnumSiteTypes;
import BitcoinAPI.Base.BitCoinApiUtils;


public class APITest 
{
    private BitcoinApi api;

    public APITest()
    {
        api = new BitcoinApi();
    }


    @Test
    public void RefreshTest() throws Exception
    {
        api.Refresh(EnumSiteTypes.Bithumb);
    }

    @Test
    public void GetValueTestAsKRW() throws Exception
    {
        api.Refresh(EnumSiteTypes.Bithumb);

        final EnumSiteTypes site = EnumSiteTypes.Bithumb;

        for(EnumCoinTypes type : EnumCoinTypes.values())
        {
            System.out.println("Coin Type : " + type.toString());
            System.out.println("    - " + site.toString() + " Max Price   :" + api.getMaxPrice(site, type));
            System.out.println("    - " + site.toString() + " Mix Price   :" + api.getMinPrice(site, type));
            System.out.println("    - " + site.toString() + " Avg Price   :" + api.getAvgPrice(site, type));
            System.out.println("    - " + site.toString() + " First Price :" + api.getFirstPrice(site, type));
            System.out.println("    - " + site.toString() + " Last Price  :" + api.getLastPrice(site, type));
            System.out.println("    - " + site.toString() + " Sell Price  :" + api.getSellPrice(site, type));
            System.out.println("    - " + site.toString() + " Buy Price   :" + api.getBuyPrice(site, type)); 
        }
    }

    @Test
    public void GetValueTestAsUSD() throws Exception
    {
        api.Refresh(EnumSiteTypes.Bithumb);
        api.setReigon(EnumReigonType.USD);




        final EnumSiteTypes site = EnumSiteTypes.Bithumb;
        for(EnumCoinTypes type : EnumCoinTypes.values())
        {
            System.out.println("Coin Type : " + type.toString());
            System.out.println("    - " + site.toString() + " Max Price   :" + api.getMaxPrice(site, type));
            System.out.println("    - " + site.toString() + " Mix Price   :" + api.getMinPrice(site, type));
            System.out.println("    - " + site.toString() + " Avg Price   :" + api.getAvgPrice(site, type));
            System.out.println("    - " + site.toString() + " First Price :" + api.getFirstPrice(site, type));
            System.out.println("    - " + site.toString() + " Last Price  :" + api.getLastPrice(site, type));
            System.out.println("    - " + site.toString() + " Sell Price  :" + api.getSellPrice(site, type));
            System.out.println("    - " + site.toString() + " Buy Price   :" + api.getBuyPrice(site, type));
        }
    }
    
}
