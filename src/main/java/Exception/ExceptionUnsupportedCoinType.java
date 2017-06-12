package Exception;

public class ExceptionUnsupportedCoinType extends Exception
{
    public ExceptionUnsupportedCoinType() {}
    public ExceptionUnsupportedCoinType(String what) 
    {
        super(what);
    }
}