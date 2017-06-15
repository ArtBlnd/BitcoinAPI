package BitcoinAPI.Base;

public enum EnumSiteTypes 
{
    Poloniex        (0),
    Bithumb         (1),
    CoinOne         (2),
    Yunbi           (3),
    Bittrex         (4),
    BitfineEx       (5),
    BitStamp        (6),
    OkCoin          (7);

    private final int value;
    private EnumSiteTypes(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}