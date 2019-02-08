package w.fujiko.exceptions;


public class QuotationTransactionCreationException extends Exception{

    private static final long serialVersionUID = 1L;

    public QuotationTransactionCreationException() {
        super();
    }
	public QuotationTransactionCreationException(String message) {super(message); }
	public QuotationTransactionCreationException(String message,Throwable cause) { super(message,cause); }
	public QuotationTransactionCreationException(Throwable cause){ super(cause);	}
}