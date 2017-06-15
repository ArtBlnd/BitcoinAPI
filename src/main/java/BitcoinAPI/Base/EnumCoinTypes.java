package BitcoinAPI.Base;

public enum EnumCoinTypes
{
    Etherium        (0),
    EtheriumClassic (1),
    Bitcoin         (2),
    Dash            (3),
    LightCoin       (4),
    Ripple          (5);

    private final int value;
    private EnumCoinTypes(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}