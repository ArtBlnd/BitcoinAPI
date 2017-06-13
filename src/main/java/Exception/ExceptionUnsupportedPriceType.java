package Exception;

public class ExceptionUnsupportedPriceType extends Exception
{
    public ExceptionUnsupportedPriceType() {}
    public ExceptionUnsupportedPriceType(String what) 
    {
        super(what);
    }
}