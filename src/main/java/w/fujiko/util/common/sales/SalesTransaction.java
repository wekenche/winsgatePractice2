package w.fujiko.util.common.sales;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import w.fujiko.exceptions.SalesTransactionCreationException;
import w.fujiko.exceptions.SalesTransactionUpdateException;
import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.util.common.sales.helpers.interfaces.ISalesTransactionCreator;
import w.fujiko.util.common.sales.helpers.interfaces.ISalesTransactionUpdater;

public class SalesTransaction {

	private SalesHeader salesHeader;
    private List<SalesDetail> salesDetails;
    
    public SalesTransaction (SalesHeader salesHeader, List<SalesDetail> salesDetails) {
        this.salesDetails = salesDetails;
        this.salesHeader = salesHeader;
    }

    public SalesHeader getSalesHeader() {
        return this.salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
    }

    public List<SalesDetail> getSalesDetails() {
        return this.salesDetails;
    }

    public void setSalesDetails(List<SalesDetail> salesDetails) {
        this.salesDetails = salesDetails;
    }

    public String getSlipNumber() {
        return this.salesHeader.getSlipNumber() != null ? 
               this.salesHeader.getSlipNumber() : "";
    }

    public void setSlipNumber(String slipNumber) {
        this.salesHeader.setSlipNumber(slipNumber);
    }

    /**
     * Checks if the transaction is existing by checking if the SalesHeader`s slip number is not null or empty
     * @return boolean
     */
    public boolean isExistingTransaction() {
        return !StringUtils.isBlank(this.salesHeader.getSlipNumber());
    }

    public SalesTransaction createNewTransaction(ISalesTransactionCreator transactionCreator) 
    	throws SalesTransactionCreationException {
        return transactionCreator.createNewTransaction(this);
    }
   
    public SalesTransaction updateTransaction(ISalesTransactionUpdater transactionUpdater) 
    	throws SalesTransactionUpdateException {
        return transactionUpdater.updateTransaction(this);
    }
	
}