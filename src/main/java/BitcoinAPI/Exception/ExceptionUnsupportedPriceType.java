package BitcoinAPI.Exception;

public class ExceptionUnsupportedPriceType extends Exception
{
    static final long serialVersionUID = 0;

    public ExceptionUnsupportedPriceType() {}
    public ExceptionUnsupportedPriceType(String what) 
    {
        super(what);
    }
}