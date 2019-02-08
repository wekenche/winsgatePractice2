package w.fujiko.util.common.quotations.helpers.interfaces;

import w.fujiko.exceptions.QuotationTransactionUpdateException;
import w.fujiko.util.common.quotations.QuotationTransaction;

public interface IQuotationTransactionUpdater {
    QuotationTransaction updateTransaction(QuotationTransaction quotationTransaction) throws QuotationTransactionUpdateException;
}