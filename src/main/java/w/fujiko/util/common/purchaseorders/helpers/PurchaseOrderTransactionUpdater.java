package w.fujiko.util.common.purchaseorders.helpers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.PurchaseOrderTransactionUpdateException;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderHeaderService;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;
import w.fujiko.util.common.purchaseorders.helpers.interfaces.IPurchaseOrderTransactionUpdater;

@Service
public class PurchaseOrderTransactionUpdater implements IPurchaseOrderTransactionUpdater {
	
	@Autowired
    private PurchaseOrderHeaderService purchaseOrderHeaderService;

	@Override
	public PurchaseOrderTransaction updateTransaction(PurchaseOrderTransaction purchaseOrderTransaction)
			throws PurchaseOrderTransactionUpdateException {
		
		if(!purchaseOrderTransaction.isExistingTransaction())
			throw new PurchaseOrderTransactionUpdateException();

		PurchaseOrderTransaction updatedPurchaseOrderTransaction = purchaseOrderTransaction;
		
		//set id to null
		updatedPurchaseOrderTransaction.getPurchaseOrderHeader().setId(null);

		//increment version
		updatedPurchaseOrderTransaction = this.incrementVersion(updatedPurchaseOrderTransaction);

		return updatedPurchaseOrderTransaction;
	}
	
	private PurchaseOrderTransaction incrementVersion(PurchaseOrderTransaction purchaseOrderTransaction)
	            throws PurchaseOrderTransactionUpdateException {
        PurchaseOrderTransaction incrementedTransactionVersion = purchaseOrderTransaction;
        try {
            //get existing PurchaseOrderHeader data and set its version incremented by 1 to new QuotationHeader data 
            PurchaseOrderHeader existingTransactionHeader = purchaseOrderHeaderService.getBySlipNumber(purchaseOrderTransaction.getSlipNumber()).orElseThrow();
            incrementedTransactionVersion.getPurchaseOrderHeader().setVersion(existingTransactionHeader.getVersion()+1);
            return incrementedTransactionVersion;
        } catch (NoSuchElementException ex) {
            throw new PurchaseOrderTransactionUpdateException();
        }        
    }

}