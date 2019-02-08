package w.fujiko.service.repo.transactions.sales;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.dao.transactions.sales.SalesDetailDao;
import w.fujiko.dao.transactions.sales.SalesHeaderDao;
import w.fujiko.dao.transactions.sales.SalesInputDao;
import w.fujiko.exceptions.SalesTransactionCreationException;
import w.fujiko.exceptions.SalesTransactionUpdateException;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.model.transactions.sales.histories.SalesHeaderHistory;
import w.fujiko.service.transactions.sales.SalesInputService;
import w.fujiko.util.common.sales.SalesTransaction;
import w.fujiko.util.common.sales.helpers.SalesTransactionCreator;
import w.fujiko.util.common.sales.helpers.SalesTransactionUpdater;

@Service
@Transactional
public class SalesInputServiceRepo implements SalesInputService {
	
	@Autowired SalesTransactionCreator transactionCreator;
    @Autowired SalesTransactionUpdater transactionUpdater;

    @Autowired SalesInputDao salesInputDao;
    @Autowired SalesHeaderDao salesHeaderDao;
    @Autowired SalesDetailDao salesDetailDao;

    ModelMapper modelMapper;

    public SalesInputServiceRepo() {
        this.modelMapper = new ModelMapper();    
        this.modelMapper.getConfiguration()
                        .setAmbiguityIgnored(true);    
    }

	@Override
	public void add(SalesTransaction transaction) throws SalesTransactionCreationException,
			SalesTransactionUpdateException, ConstraintViolationException, PersistenceException {
		
		if(transaction.isExistingTransaction()){
            SalesHeader pastSalesHeader = salesHeaderDao.getBySlipNumber(transaction.getSlipNumber()).orElseThrow();
            var headerHistory = new SalesHeaderHistory();
          
            transaction = transaction.updateTransaction(transactionUpdater);           
            salesInputDao.save(transaction);

            try {
                headerHistory.setId(pastSalesHeader.getId());
                salesInputDao.deleteAndMoveToHistory(headerHistory);    
                return;
            } catch(Exception e){
                e.printStackTrace(); 
                salesDetailDao.deleteBySalesHeaderId(transaction.getSalesHeader().getId());
                salesHeaderDao.deleteById(transaction.getSalesHeader().getId());
                throw e;
            }

        }
        
        transaction = transaction.createNewTransaction(transactionCreator);
        
        salesInputDao.save(transaction);
		
	}

}