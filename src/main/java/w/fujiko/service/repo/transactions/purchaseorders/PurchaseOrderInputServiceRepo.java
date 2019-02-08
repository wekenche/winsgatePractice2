package w.fujiko.service.repo.transactions.purchaseorders;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderDetailDao;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderHeaderDao;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderInputDao;
import w.fujiko.exceptions.PurchaseOrderTransactionCreationException;
import w.fujiko.exceptions.PurchaseOrderTransactionUpdateException;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.model.transactions.purchaseorders.histories.PurchaseOrderHeaderHistory;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderInputService;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;
import w.fujiko.util.common.purchaseorders.helpers.PurchaseOrderTransactionCreator;
import w.fujiko.util.common.purchaseorders.helpers.PurchaseOrderTransactionUpdater;

@Service
@Transactional
public class PurchaseOrderInputServiceRepo implements PurchaseOrderInputService {
	
	@Autowired PurchaseOrderTransactionCreator transactionCreator;
    @Autowired PurchaseOrderTransactionUpdater transactionUpdater;

    @Autowired PurchaseOrderInputDao purchaseOrderInputDao;
    @Autowired PurchaseOrderHeaderDao purchaseOrderHeaderDao;
    @Autowired PurchaseOrderDetailDao purchaseOrderDetailDao;

    ModelMapper modelMapper;

    public PurchaseOrderInputServiceRepo() {
        this.modelMapper = new ModelMapper();    
        this.modelMapper.getConfiguration()
                        .setAmbiguityIgnored(true);    
    }

	@Override
	public void add(PurchaseOrderTransaction transaction) throws PurchaseOrderTransactionCreationException,
			PurchaseOrderTransactionUpdateException, ConstraintViolationException, PersistenceException {
		
		if(transaction.isExistingTransaction()){
            PurchaseOrderHeader pastPurchaseOrderHeader = purchaseOrderHeaderDao.getBySlipNumber(transaction.getSlipNumber()).orElseThrow();
            PurchaseOrderHeaderHistory headerHistory = new PurchaseOrderHeaderHistory();
          
            transaction = transaction.updateTransaction(transactionUpdater);           
            purchaseOrderInputDao.save(transaction);
            headerHistory.setId(pastPurchaseOrderHeader.getId());
            purchaseOrderInputDao.deleteAndMoveToHistory(headerHistory);    
            return;
        }
        
        transaction = transaction.createNewTransaction(transactionCreator);
        purchaseOrderInputDao.save(transaction);
		
	}

}