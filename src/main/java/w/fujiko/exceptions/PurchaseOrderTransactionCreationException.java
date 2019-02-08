package w.fujiko.exceptions;

public class PurchaseOrderTransactionCreationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public PurchaseOrderTransactionCreationException() {
		super();
	}
	public PurchaseOrderTransactionCreationException(String message) {super(message); }
	public PurchaseOrderTransactionCreationException(String message,Throwable cause) { super(message,cause); }
	public PurchaseOrderTransactionCreationException(Throwable cause){ super(cause);	}

}