package w.fujiko.exceptions;

public class SalesTransactionCreationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public SalesTransactionCreationException() {
		super();
	}
	public SalesTransactionCreationException(String message) {super(message); }
	public SalesTransactionCreationException(String message,Throwable cause) { super(message,cause); }
	public SalesTransactionCreationException(Throwable cause){ super(cause);	}

}