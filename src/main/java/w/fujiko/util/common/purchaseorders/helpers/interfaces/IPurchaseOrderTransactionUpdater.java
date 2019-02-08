package w.fujiko.util.common.purchaseorders.helpers.interfaces;

import w.fujiko.exceptions.PurchaseOrderTransactionUpdateException;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;

public interface IPurchaseOrderTransactionUpdater {
	public PurchaseOrderTransaction updateTransaction(
			PurchaseOrderTransaction purchaseOrderTransaction) throws PurchaseOrderTransactionUpdateException;
}