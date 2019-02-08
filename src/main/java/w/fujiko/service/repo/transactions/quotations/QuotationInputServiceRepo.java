package w.fujiko.service.repo.transactions.quotations;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.dao.transactions.quotations.QuotationDetailDao;
import w.fujiko.dao.transactions.quotations.QuotationHeaderDao;
import w.fujiko.dao.transactions.quotations.QuotationInputDao;
import w.fujiko.exceptions.QuotationTransactionCreationException;
import w.fujiko.exceptions.QuotationTransactionUpdateException;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory;
import w.fujiko.service.transactions.quotations.QuotationInputService;
import w.fujiko.util.common.quotations.QuotationTransaction;
import w.fujiko.util.common.quotations.helpers.QuotationTransactionCreator;
import w.fujiko.util.common.quotations.helpers.QuotationTransactionUpdater;
 
@Service
@Transactional
public class QuotationInputServiceRepo implements QuotationInputService {

    @Autowired QuotationTransactionCreator transactionCreator;
    @Autowired QuotationTransactionUpdater transactionUpdater;

    @Autowired QuotationInputDao quotationInputDao;
    @Autowired QuotationHeaderDao quotationHeaderDao;
    @Autowired QuotationDetailDao quotationDetailDao;

    ModelMapper modelMapper;

    public QuotationInputServiceRepo(){
        this.modelMapper = new ModelMapper();    
        this.modelMapper.getConfiguration()
                        .setAmbiguityIgnored(true);    
    }
    
    @Override
    public void add(QuotationTransaction transaction)
         throws QuotationTransactionCreationException,
                QuotationTransactionUpdateException,
                ConstraintViolationException,
                PersistenceException {
        
        if(transaction.isExistingTransaction()){
            QuotationHeader pastQuotationHeader = quotationHeaderDao.getBySlipNumber(transaction.getSlipNumber()).orElseThrow();
            QuotationHeaderHistory headerHistory = new QuotationHeaderHistory();
          
            transaction = transaction.updateTransaction(transactionUpdater);           
            quotationInputDao.save(transaction);
            headerHistory.setId(pastQuotationHeader.getId());
            quotationInputDao.deleteAndMoveToHistory(headerHistory);    
            return;
        }
        
        transaction = transaction.createNewTransaction(transactionCreator);
        
        quotationInputDao.save(transaction);
        
    }
}
