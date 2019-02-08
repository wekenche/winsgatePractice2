package w.fujiko.exceptions;

public class SalesTransactionUpdateException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SalesTransactionUpdateException() {
		super();
	}
	public SalesTransactionUpdateException(String message) {super(message); }
	public SalesTransactionUpdateException(String message,Throwable cause) { super(message,cause); }
	public SalesTransactionUpdateException(Throwable cause){ super(cause);	}
}