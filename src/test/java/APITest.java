import java.io.Console;

import org.junit.Assert;
import org.junit.Test;

import BitcoinAPI.BitcoinApi;
import BitcoinAPI.Base.EnumCoinTypes;
import BitcoinAPI.Base.EnumSiteTypes;


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
    public void GetValueTest() throws Exception
    {
        api.Refresh(EnumSiteTypes.Bithumb);

        final EnumSiteTypes site = EnumSiteTypes.Bithumb;

        for(EnumCoinTypes type : EnumCoinTypes.values())
        {
            System.out.println("Coin Type : " + type.toString());
            System.out.println("    - " + site.toString() + " Max Price   :" + api.getMaxPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
            System.out.println("    - " + site.toString() + " Mix Price   :" + api.getMinPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
            System.out.println("    - " + site.toString() + " Avg Price   :" + api.getAvgPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
            System.out.println("    - " + site.toString() + " First Price :" + api.getFirstPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
            System.out.println("    - " + site.toString() + " Last Price  :" + api.getLastPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
            System.out.println("    - " + site.toString() + " Sell Price  :" + api.getSellPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
            System.out.println("    - " + site.toString() + " Buy Price   :" + api.getBuyPrice(EnumSiteTypes.Bithumb, EnumCoinTypes.Bitcoin));
        }
    }
    
}
