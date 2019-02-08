package w.fujiko.util.common.sales.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SalesTransactionCreationException;
import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.service.transactions.numgens.SlipNumberGeneratorService;
import w.fujiko.util.common.generator.slips.SalesSlipNumGenerator;
import w.fujiko.util.common.sales.SalesTransaction;
import w.fujiko.util.common.sales.helpers.interfaces.ISalesTransactionCreator;

@Service
public class SalesTransactionCreator implements ISalesTransactionCreator {

	@Autowired
	private SlipNumberGeneratorService slipNumberGeneratorService;
	@Autowired
	private SalesSlipNumGenerator salesSlipNumberGenerator;

	private final int VERSION1=1;

	@Override
	public SalesTransaction createNewTransaction(SalesTransaction salesTransaction)
			throws SalesTransactionCreationException {
		
		SalesTransaction newTransaction = salesTransaction;
        
        //set Id to null
        newTransaction.getSalesHeader().setId(null);
        
        //Set sales slip number
        String slipNumber = this.generateSlipNumber(newTransaction.getSalesHeader().getUser().getId());
        newTransaction.setSlipNumber(slipNumber);

        //set version
        newTransaction.getSalesHeader().setVersion(VERSION1);

        //set taskIds
        newTransaction = this.setTaskIds(salesTransaction);

        return newTransaction;
	}
	
	//set slipNumber using userid
    private String generateSlipNumber(Integer userId) throws SalesTransactionCreationException {
        String slipNumber = "";
        try {
            slipNumber = this.slipNumberGeneratorService.generateSlipNumber(salesSlipNumberGenerator, userId);
            return slipNumber;
        } catch (SlipNumberGenerationException e) {         
            e.printStackTrace();
            throw new SalesTransactionCreationException();
        }
    }

    //set lineNumber as taskId
    private SalesTransaction setTaskIds(SalesTransaction salesTransaction) throws SalesTransactionCreationException {
        SalesTransaction salesTransactionWithTaskId = salesTransaction;
        salesTransactionWithTaskId.getSalesDetails().forEach(e->e.setTaskId((short)e.getLineNumber()));
        return salesTransactionWithTaskId;
    }

}