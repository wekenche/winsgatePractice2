package w.fujiko.dao.transactions.purchaseorders;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import w.fujiko.model.transactions.purchaseorders.histories.PurchaseOrderHeaderHistory;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;

public interface PurchaseOrderInputDao {
	public void save(PurchaseOrderTransaction purchaseOrderTransaction) throws ConstraintViolationException, PersistenceException;
	public void deleteAndMoveToHistory(PurchaseOrderHeaderHistory headerHistory) throws ConstraintViolationException, PersistenceException;
}
