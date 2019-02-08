package w.fujiko.util.common.purchaseorders;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import w.fujiko.exceptions.PurchaseOrderTransactionCreationException;
import w.fujiko.exceptions.PurchaseOrderTransactionUpdateException;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.util.common.purchaseorders.helpers.interfaces.IPurchaseOrderTransactionCreator;
import w.fujiko.util.common.purchaseorders.helpers.interfaces.IPurchaseOrderTransactionUpdater;

public class PurchaseOrderTransaction {

	private PurchaseOrderHeader purchaseOrderHeader;
    private List<PurchaseOrderDetail> purchaseOrderDetails;
    
    public PurchaseOrderTransaction (PurchaseOrderHeader purchaseOrderHeader, List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
        this.purchaseOrderHeader = purchaseOrderHeader;
    }

    public PurchaseOrderHeader getPurchaseOrderHeader() {
        return this.purchaseOrderHeader;
    }

    public void setPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
        this.purchaseOrderHeader = purchaseOrderHeader;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return this.purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    public String getSlipNumber() {
        return this.purchaseOrderHeader.getSlipNumber() != null ? 
               this.purchaseOrderHeader.getSlipNumber() : "";
    }

    public void setSlipNumber(String slipNumber) {
        this.purchaseOrderHeader.setSlipNumber(slipNumber);
    }

    /**
     * Checks if the transaction is existing by checking if the PurchaseOrderHeader`s slip number is not null or empty
     * @return boolean
     */
    public boolean isExistingTransaction() {
        return !StringUtils.isBlank(this.purchaseOrderHeader.getSlipNumber());
    }

    public PurchaseOrderTransaction createNewTransaction(IPurchaseOrderTransactionCreator transactionCreator) 
    	throws PurchaseOrderTransactionCreationException {
        return transactionCreator.createNewTransaction(this);
    }
   
    public PurchaseOrderTransaction updateTransaction(IPurchaseOrderTransactionUpdater transactionUpdater) 
    	throws PurchaseOrderTransactionUpdateException {
        return transactionUpdater.updateTransaction(this);
    }
	
}