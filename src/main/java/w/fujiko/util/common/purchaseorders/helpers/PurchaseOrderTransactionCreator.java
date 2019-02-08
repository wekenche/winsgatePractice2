 package w.fujiko.util.common.purchaseorders.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.PurchaseOrderTransactionCreationException;
import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.service.transactions.numgens.SlipNumberGeneratorService;
import w.fujiko.util.common.generator.slips.OrderSlipNumGenerator;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;
import w.fujiko.util.common.purchaseorders.helpers.interfaces.IPurchaseOrderTransactionCreator;

@Service
public class PurchaseOrderTransactionCreator implements IPurchaseOrderTransactionCreator {

	@Autowired
	private SlipNumberGeneratorService slipNumberGeneratorService;
	@Autowired
	private OrderSlipNumGenerator orderSlipNumberGenerator;

	private final int VERSION1=1;

	@Override
	public PurchaseOrderTransaction createNewTransaction(PurchaseOrderTransaction purchaseOrderTransaction)
			throws PurchaseOrderTransactionCreationException {
		
		PurchaseOrderTransaction newTransaction = purchaseOrderTransaction;
        
        //set Id to null
        newTransaction.getPurchaseOrderHeader().setId(null);
        
        //Set purchase order slip number
        String slipNumber = this.generateSlipNumber(newTransaction.getPurchaseOrderHeader().getUser().getId());
        newTransaction.setSlipNumber(slipNumber);

        //set version
        newTransaction.getPurchaseOrderHeader().setVersion(VERSION1);

        //set taskIds
        newTransaction = this.setTaskIds(purchaseOrderTransaction);

        return newTransaction;
	}
	
	//set slipNumber using userid
    private String generateSlipNumber(Integer userId) throws PurchaseOrderTransactionCreationException {
        String slipNumber = "";
        try {
            slipNumber = this.slipNumberGeneratorService.generateSlipNumber(orderSlipNumberGenerator, userId);
            return slipNumber;
        } catch (SlipNumberGenerationException e) {         
            e.printStackTrace();
            throw new PurchaseOrderTransactionCreationException();
        }
    }

    //set lineNumber as taskId
    private PurchaseOrderTransaction setTaskIds(PurchaseOrderTransaction purchaseOrderTransaction) throws PurchaseOrderTransactionCreationException {
        PurchaseOrderTransaction purchaseOrderTransactionWithTaskId = purchaseOrderTransaction;
        purchaseOrderTransactionWithTaskId.getPurchaseOrderDetails().forEach(e->e.getId().setTaskId((short)e.getLineNumber()));
        return purchaseOrderTransactionWithTaskId;
    }

}