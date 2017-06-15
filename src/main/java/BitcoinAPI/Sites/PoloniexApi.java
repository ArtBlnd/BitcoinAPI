package BitcoinAPI.Sites;

import BitcoinAPI.Base.IBitcoinApiBase;
import BitcoinAPI.Base.EnumCoinTypes;
import BitcoinAPI.Base.EnumReigonType;
import BitcoinAPI.Base.EnumCoinTypes;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import BitcoinAPI.Base.CoinInfo;
import BitcoinAPI.Exception.ExceptionUnsupportedPriceType;


// Poloniex only supports bitcoin - bitcoin trade. so we will not support any methods named getXXXXPrice()
// Poloniex�� ���� ��Ʈ���� �� ��Ʈ���� �ŷ��� �����մϴ�. �׷��Ƿ� ������� getXXXXPrice()�� �̸������� �޼ҵ���� �������� ���� ���Դϴ�.
public class PoloniexApi implements IBitcoinApiBase
{
    HashMap<EnumCoinTypes, CoinInfo> cachedInfo;

    private final String targetURL = "https://poloniex.com/public?command=returnTicker";
    private String cachedJsonData;

    public PoloniexApi()
    {
        cachedInfo = new HashMap<EnumCoinTypes,CoinInfo>();
    }

    private String CoinTypeToToken(EnumCoinTypes type)
    {
        String token = new String();

        token += "BTC_";

        switch(type)
        {
            case Bitcoin: token += "BTC";
            case Etherium: token += "ETH";
            case Dash: token += "DASH";
            case LightCoin: token += "LTC";
            case Ripple: token += "XRP";
            case EtheriumClassic: token += "ETC";
        }

        return token;
    }

    public EnumCoinTypes[] getAvailableCoinTypes()
    {
        return new EnumCoinTypes[]
        {
            
        };
    }
    public void Refresh()
    {
        try {
            URL               reqestURL = new URL(targetURL);
            InputStreamReader stream = new InputStreamReader(reqestURL.openConnection().getInputStream(), "UTF-8");

        } catch(Exception e) {

        }
    }
    public void Refresh(EnumCoinTypes type)
    {
        
    }

    // NOT SUPPORTING!!
    // �������� �ʽ��ϴ�!!
    public void setReigon(EnumReigonType type) { }
    public double getMaxPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getMinPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getAvgPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getFirstPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getLastPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getSellPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }
    public double getBuyPrice(EnumCoinTypes type) throws ExceptionUnsupportedPriceType { throw new ExceptionUnsupportedPriceType(); }

    public CoinInfo getRatio(IBitcoinApiBase target, EnumCoinTypes coinType) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        return info;
    }
    public CoinInfo getRatioOfCoin(EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        return info;
    }
    public CoinInfo getRatioAsCoin(IBitcoinApiBase target, EnumCoinTypes baseCoin, EnumCoinTypes targetCoin) throws ExceptionUnsupportedPriceType
    {
        CoinInfo info = new CoinInfo();

        return info;
    }
}