package w.fujiko.util.common.sales.helpers.interfaces;

import w.fujiko.exceptions.SalesTransactionUpdateException;
import w.fujiko.util.common.sales.SalesTransaction;

public interface ISalesTransactionUpdater {
	public SalesTransaction updateTransaction(
        SalesTransaction salesTransaction) throws SalesTransactionUpdateException;
}