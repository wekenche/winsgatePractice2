package w.fujiko.util.common.quotations.helpers.interfaces;

import w.fujiko.exceptions.QuotationTransactionCreationException;
import w.fujiko.util.common.quotations.QuotationTransaction;

public interface IQuotationTransactionCreator {
    QuotationTransaction createNewTransaction(QuotationTransaction quotationTransaction) throws QuotationTransactionCreationException;
}