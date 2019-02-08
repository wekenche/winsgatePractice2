package w.fujiko.dao.transactions.sales;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import w.fujiko.model.transactions.sales.histories.SalesHeaderHistory;
import w.fujiko.util.common.sales.SalesTransaction;

public interface SalesInputDao {
	public void save(SalesTransaction salesTransaction) throws ConstraintViolationException, PersistenceException;
	public void deleteAndMoveToHistory(SalesHeaderHistory headerHistory) throws ConstraintViolationException, PersistenceException;
}
