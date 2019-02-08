package w.fujiko.dao.transactions.quotations;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory;
import w.fujiko.util.common.quotations.QuotationTransaction;

public interface QuotationInputDao {
    void save(QuotationTransaction transaction) throws ConstraintViolationException,PersistenceException;
    void deleteAndMoveToHistory(QuotationHeaderHistory headerHistory)throws ConstraintViolationException,PersistenceException;
}