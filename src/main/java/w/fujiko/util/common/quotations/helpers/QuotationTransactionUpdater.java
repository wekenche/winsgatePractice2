package w.fujiko.util.common.quotations.helpers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.QuotationTransactionUpdateException;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.service.transactions.quotations.QuotationHeaderService;
import w.fujiko.util.common.quotations.QuotationTransaction;
import w.fujiko.util.common.quotations.helpers.interfaces.IQuotationTransactionUpdater;

@Service
public class QuotationTransactionUpdater implements IQuotationTransactionUpdater {
    
    @Autowired
    private QuotationHeaderService quotationHeaderService;
    
    @Override
    public QuotationTransaction updateTransaction(QuotationTransaction quotationTransaction)
            throws QuotationTransactionUpdateException {
        
        if(!quotationTransaction.isExistingTransaction())
            throw new QuotationTransactionUpdateException();

        QuotationTransaction updatedQuotationTransaction = quotationTransaction;
        
        //set id to null
        updatedQuotationTransaction.getQuotationHeader().setId(null);

        //increment version
        updatedQuotationTransaction = this.incrementVersion(updatedQuotationTransaction);

        return updatedQuotationTransaction;
    }

    private QuotationTransaction incrementVersion(QuotationTransaction quotationTransaction)
            throws QuotationTransactionUpdateException {
        QuotationTransaction incrementedTransactionVersion = quotationTransaction;
        try {
            //get existing QuotationHeader data and set its version incremented by 1 to new QuotationHeader data 
            QuotationHeader existingTransactionHeader = quotationHeaderService.getBySlipNumber(quotationTransaction.getSlipNumber()).orElseThrow();
            incrementedTransactionVersion.getQuotationHeader().setVersion(existingTransactionHeader.getVersion()+1);
            return incrementedTransactionVersion;
        } catch (NoSuchElementException ex) {
            throw new QuotationTransactionUpdateException();
        }        
    }

}