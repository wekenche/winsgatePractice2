package w.fujiko.util.common.purchaseorders.helpers.interfaces;

import w.fujiko.exceptions.PurchaseOrderTransactionCreationException;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;

public interface IPurchaseOrderTransactionCreator {
	public PurchaseOrderTransaction createNewTransaction(
			PurchaseOrderTransaction purchaseOrderTransaction) throws PurchaseOrderTransactionCreationException;
}