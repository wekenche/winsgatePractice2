package w.fujiko.service.transactions.quotations;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import w.fujiko.exceptions.QuotationTransactionCreationException;
import w.fujiko.exceptions.QuotationTransactionUpdateException;
import w.fujiko.util.common.quotations.QuotationTransaction;

@Service
public interface QuotationInputService {
    void add(QuotationTransaction transaction) 
            throws QuotationTransactionCreationException,
            QuotationTransactionUpdateException,
            ConstraintViolationException,
            PersistenceException;
}