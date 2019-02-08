package w.fujiko.util.common.quotations.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.QuotationTransactionCreationException;
import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.service.transactions.numgens.SlipNumberGeneratorService;
import w.fujiko.util.common.quotations.QuotationTransaction;
import w.fujiko.util.common.quotations.helpers.interfaces.IQuotationTransactionCreator;
import w.fujiko.util.common.generator.slips.QuotationSlipNumGenerator;

@Service
public class QuotationTransactionCreator implements IQuotationTransactionCreator {
    
    @Autowired
    private SlipNumberGeneratorService slipNumberGeneratorService;
    @Autowired
    private QuotationSlipNumGenerator quotationSlipNumberGenerator;

    private final byte VERSION1=1;

    @Override
    public QuotationTransaction createNewTransaction(QuotationTransaction quotationTransaction)
            throws QuotationTransactionCreationException {
        QuotationTransaction newTransaction = quotationTransaction;
        
        //set Id to null
        newTransaction.getQuotationHeader().setId(null);
        
        //Set quotation slip number
        String slipNumber = this.generateSlipNumber(newTransaction.getQuotationHeader().getUser().getId());
        newTransaction.setSlipNumber(slipNumber);

        //set version
        newTransaction.getQuotationHeader().setVersion(VERSION1);

        //set taskIds
        newTransaction = this.setTaskIds(quotationTransaction);

        return newTransaction;
    }
    
    //set slipNumber using userid
    private String generateSlipNumber(Integer userId) throws QuotationTransactionCreationException {
        String slipNumber = "";
        try {
            slipNumber = this.slipNumberGeneratorService.generateSlipNumber(quotationSlipNumberGenerator, userId);
            return slipNumber;
        } catch (SlipNumberGenerationException e) {         
            e.printStackTrace();
            throw new QuotationTransactionCreationException();
        }
    }

    //set lineNumber as taskId
    private QuotationTransaction setTaskIds(QuotationTransaction quotationTransaction) throws QuotationTransactionCreationException {
        QuotationTransaction quotationTransactionWithTaskId = quotationTransaction;
        quotationTransactionWithTaskId.getQuotationDetails().forEach(e->e.setTaskId((short)e.getLineNumber()));
        return quotationTransactionWithTaskId;
    }

}