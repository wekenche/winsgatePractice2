package w.fujiko.exceptions;

public class PurchaseOrderTransactionUpdateException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PurchaseOrderTransactionUpdateException() {
		super();
	}
	public PurchaseOrderTransactionUpdateException(String message) {super(message); }
	public PurchaseOrderTransactionUpdateException(String message,Throwable cause) { super(message,cause); }
	public PurchaseOrderTransactionUpdateException(Throwable cause){ super(cause);	}
}