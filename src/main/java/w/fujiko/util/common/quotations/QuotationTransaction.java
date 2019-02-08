package w.fujiko.util.common.quotations;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import w.fujiko.exceptions.QuotationTransactionCreationException;
import w.fujiko.exceptions.QuotationTransactionUpdateException;
import w.fujiko.model.transactions.quotations.QuotationDetail;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.util.common.quotations.helpers.interfaces.IQuotationTransactionCreator;
import w.fujiko.util.common.quotations.helpers.interfaces.IQuotationTransactionUpdater;

public class QuotationTransaction {

    private QuotationHeader quotationHeader;
    private List<QuotationDetail> quotationDetails;
    
    public QuotationTransaction (QuotationHeader quotationHeader,List<QuotationDetail> quotationDetails) {
        this.quotationDetails = quotationDetails;
        this.quotationHeader = quotationHeader;
    }

    public QuotationHeader getQuotationHeader() {
        return this.quotationHeader;
    }

    public void setQuotationHeader(QuotationHeader quotationHeader) {
        this.quotationHeader = quotationHeader;
    }

    public List<QuotationDetail> getQuotationDetails() {
        return this.quotationDetails;
    }

    public void setQuotationDetails(List<QuotationDetail> quotationDetails) {
        this.quotationDetails = quotationDetails;
    }

    public String getSlipNumber() {
        return this.quotationHeader.getSlipNumber() != null ? 
               this.quotationHeader.getSlipNumber() : "";
    }

    public void setSlipNumber(String slipNumber) {
        this.quotationHeader.setSlipNumber(slipNumber);
    }

    /**
     * Checks if the transaction is existing by checking if the QuotationHeader`s slip number is not null or empty
     * @return boolean
     */
    public boolean isExistingTransaction() {
        return !StringUtils.isBlank(this.quotationHeader.getSlipNumber());
    }

    /**
     * Creates a new transaction using the provided IQuotationTransactionCreator
     * @param transactionCreator - the object that provides the business logic of creating a new quotation transaction
     * @return new QuotationTransaction object
     * @throws QuotationTransactionCreationException
     */
    public QuotationTransaction createNewTransaction(IQuotationTransactionCreator transactionCreator) throws QuotationTransactionCreationException{
        return transactionCreator.createNewTransaction(this);
    }

    /**
     * Updates an existing transaction using the provided IQuotationTransactionUpdator
     * @param transactionUpdater - the object that provides the business logic of updating an existing quotation transaction
     * @return updated QuotationTransaction object
     * @throws QuotationTransactionUpdateException
     */
    public QuotationTransaction updateTransaction(IQuotationTransactionUpdater transactionUpdater) throws QuotationTransactionUpdateException{
        return transactionUpdater.updateTransaction(this);
    }

}
