package fte.api.universal;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import fte.api.Page;

/**
 * @author Try-Parser
 * @category fte.api
 */
public interface UniversalCrud <T, I extends Serializable> {
	
	/**
	 * @param first
	 * @param max
	 * @return Page
	 * @see fte.api.Page
	 */
	public Page<T> paginate(int first, int max);
	
	/**
	 * 
	 * @param first
	 * @param max
	 * @param flagValue
	 * @return
	 */
	public Page<T> paginate(int first, int max, Boolean flagValue);
	
	/**
	 * 
	 * @param first
	 * @param max
	 * @param flagField
	 * @return
	 */
	public Page<T> paginate(int first, int max, String flagField);
	
	/**
	 * 
	 * @param first
	 * @param max
	 * @param isEnd
	 * @return
	 */
	public Page<T> paginate(int first, int max, String flagField, Boolean flagValue);
	
	/**
	 * @return List
	 */
	public List<T> get();
	
	/**
	 * @param id
	 * @return Optional
	 */
	public Optional<T> get(I id);
	
	/**
	 * @param t
	 * @see fte.api.response
	 */
	public void saveOrUpdate(T t) throws ConstraintViolationException,PersistenceException;
	
	/**
	 * 
	 * @param flag
	 * @return Boolean
	 */
	public void delete(T t) throws ConstraintViolationException,PersistenceException;

	/**
	 * 
	 * @param flag
	 * @return Boolean
	 */
	public void disable(Boolean flag, I id) throws Exception;
}
