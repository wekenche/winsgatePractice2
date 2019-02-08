package w.fujiko.service.transactions.sales;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SalesTransactionCreationException;
import w.fujiko.exceptions.SalesTransactionUpdateException;

import w.fujiko.util.common.sales.SalesTransaction;

@Service
public interface SalesInputService {
	public void add(SalesTransaction transaction) 
			throws SalesTransactionCreationException,
			SalesTransactionUpdateException,
			ConstraintViolationException,
			PersistenceException;
}