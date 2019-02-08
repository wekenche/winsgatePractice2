package w.fujiko.util.common.sales.helpers.interfaces;

import w.fujiko.exceptions.SalesTransactionCreationException;
import w.fujiko.util.common.sales.SalesTransaction;

public interface ISalesTransactionCreator {
	public SalesTransaction createNewTransaction(
        SalesTransaction salesTransaction) throws SalesTransactionCreationException;
}