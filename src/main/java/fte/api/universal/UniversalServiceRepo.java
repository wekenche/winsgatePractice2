/**
 * 
 */
package fte.api.universal;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.Page;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class UniversalServiceRepo<C, D extends UniversalCrud<C, I>, I extends Serializable> implements UniversalCrud<C, I> {

	public @Autowired D dao;
	
	@Override
	public Page<C> paginate(int first, int max) {
		return dao.paginate(first, max);
	}
	
	@Override
	public Page<C> paginate(int first, int max, Boolean flagValue) {
		return dao.paginate(first, max, flagValue);
	}
	
	@Override
	public Page<C> paginate(int first, int max, String flagField) {
		return dao.paginate(first, max, flagField);
	}
	
	@Override
	public Page<C> paginate(int first, int max, String flagField, Boolean flagValue) {
		return dao.paginate(first, max, flagField, flagValue);
	}

	@Override
	public List<C> get() {
		return dao.get();
	}

	@Override
	public Optional<C> get(I id) {
		return dao.get(id);
	}

	@Override
	public void saveOrUpdate(C t) throws ConstraintViolationException,PersistenceException {
		dao.saveOrUpdate(t);
	}

	@Override
	public void delete(C t) throws ConstraintViolationException,PersistenceException {
		dao.delete(t);
	}

	@Override
	public void disable(Boolean flag, I id) throws Exception {
		dao.disable(flag, id);
	}

}
