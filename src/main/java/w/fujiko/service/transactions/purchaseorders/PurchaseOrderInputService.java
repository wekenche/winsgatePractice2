package w.fujiko.service.transactions.purchaseorders;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import w.fujiko.exceptions.PurchaseOrderTransactionCreationException;
import w.fujiko.exceptions.PurchaseOrderTransactionUpdateException;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;

@Service
public interface PurchaseOrderInputService {
	public void add(PurchaseOrderTransaction transaction) 
			throws PurchaseOrderTransactionCreationException,
			PurchaseOrderTransactionUpdateException,
			ConstraintViolationException,
			PersistenceException;
}