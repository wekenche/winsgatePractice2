package w.fujiko.util.common.sales.helpers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SalesTransactionUpdateException;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.service.transactions.sales.SalesHeaderService;
import w.fujiko.util.common.sales.SalesTransaction;
import w.fujiko.util.common.sales.helpers.interfaces.ISalesTransactionUpdater;

@Service
public class SalesTransactionUpdater implements ISalesTransactionUpdater {
	
	@Autowired
    private SalesHeaderService salesHeaderService;

	@Override
	public SalesTransaction updateTransaction(SalesTransaction salesTransaction)
			throws SalesTransactionUpdateException {
		
		if(!salesTransaction.isExistingTransaction())
			throw new SalesTransactionUpdateException();

		SalesTransaction updatedSalesTransaction = salesTransaction;
		
		//set id to null
		updatedSalesTransaction.getSalesHeader().setId(null);

		//increment version
		updatedSalesTransaction = this.incrementVersion(updatedSalesTransaction);

		return updatedSalesTransaction;
	}
	
	private SalesTransaction incrementVersion(SalesTransaction salesTransaction)
	            throws SalesTransactionUpdateException {
        SalesTransaction incrementedTransactionVersion = salesTransaction;
        try {
            //get existing SalesOrderHeader data and set its version incremented by 1 to new SalesHeader data 
            SalesHeader existingTransactionHeader = salesHeaderService.getBySlipNumber(salesTransaction.getSlipNumber()).orElseThrow();
            incrementedTransactionVersion.getSalesHeader().setVersion(existingTransactionHeader.getVersion()+1);
            return incrementedTransactionVersion;
        } catch (NoSuchElementException ex) {
            throw new SalesTransactionUpdateException();
        }        
    }

}