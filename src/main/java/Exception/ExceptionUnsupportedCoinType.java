package Exception;

public class ExceptionUnsupportedCoinType extends Exception
{
    static final long serialVersionUID = 0;

    public ExceptionUnsupportedCoinType() {}
    public ExceptionUnsupportedCoinType(String what) 
    {
        super(what);
    }
}