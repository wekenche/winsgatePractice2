package w.fujiko.dao.repo.banks;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.banks.BankDao;
import w.fujiko.model.masters.banks.Bank;
import w.fujiko.util.common.constants.BankConstants;

@Repository
@Transactional
public class BankDaoRepo extends UniversalCrudRepo<Bank, Integer> 
	implements BankDao {
	
	public BankDaoRepo() {
		super();
		type = Bank.class;
	}

	@Override
	public List<Bank> getBanksByNameOrCode(String key, Integer index) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Bank> criteria = builder.createQuery(type);
		Root<Bank> root = criteria.from(type);
		
		int code;
		try {
			code = Integer.parseInt(key);
		} catch(NumberFormatException nfe) {
			code = -1;
		}
		
		criteria.select(root)
			.where(
				builder.or(
					builder.equal(root.get(BankConstants.FIELD_BANK_CODE), code), 
					builder.like(
						builder.lower(root.get(BankConstants.FIELD_BANK_NAME)), 
						"%"+key+"%"
					)
				)
			);
		
		List<Bank> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<Bank> getUndeletedBankByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Bank> criteria = builder.createQuery(type);
		Root<Bank> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(BankConstants.FIELD_BANK_CODE), code), 
					builder.equal(root.get(BankConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<Bank> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public List<Bank> getBanksByCode(Integer from, Integer to) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Bank> criteria = builder.createQuery(type);
		criteria.distinct(true);
		Root<Bank> root = criteria.from(type);
		root.fetch(BankConstants.FIELD_BRANCHES, JoinType.INNER);
		
		criteria.select(root)
			.where(
				builder.between(
					root.get(BankConstants.FIELD_BANK_CODE), from, to)
				);
		
		List<Bank> t = session.createQuery(criteria)
			.getResultList();
		
		session.close();
		return t;
		
	}

}