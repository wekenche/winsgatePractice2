package w.fujiko.exceptions;


public class QuotationTransactionUpdateException extends Exception{

    private static final long serialVersionUID = 1L;

    public QuotationTransactionUpdateException() {
        super();
    }
	public QuotationTransactionUpdateException(String message) {super(message); }
	public QuotationTransactionUpdateException(String message,Throwable cause) { super(message,cause); }
	public QuotationTransactionUpdateException(Throwable cause){ super(cause);	}
}