package w.fujiko.dao.repo.banks;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.banks.BankBranchDao;
import w.fujiko.model.masters.banks.BankBranch;
import w.fujiko.util.common.constants.BankConstants;

@Repository
@Transactional
public class BankBranchDaoRepo  extends UniversalCrudRepo<BankBranch, Integer> 
	implements BankBranchDao {
	
	public BankBranchDaoRepo() {
		super();
		type = BankBranch.class;
	}

	@Override
	public List<BankBranch> getBranchesByBankId(Integer bankId, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<BankBranch> criteria = builder.createQuery(type);
		Root<BankBranch> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.equal(
					root.get(BankConstants.FIELD_BANK)
						.get(BankConstants.FIELD_BANK_ID), 
					bankId
				)
			);
		
		List<BankBranch> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public List<BankBranch> getBranchesByNameOrCode(Integer bankId, String key, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<BankBranch> criteria = builder.createQuery(type);
		Root<BankBranch> root = criteria.from(type);
		
		int code;
		try {
			code = Integer.parseInt(key);
		} catch(NumberFormatException nfe) {
			code = -1;
		}
		
		criteria.select(root)
			.where(builder.and(
				builder.equal(root.get(BankConstants.FIELD_BANK)
									.get(BankConstants.FIELD_BANK_ID), bankId),
				builder.or(
					builder.equal(root.get(BankConstants.FIELD_BANK_BRANCH_CODE), code),
					builder.like(
						builder.lower(root.get(BankConstants.FIELD_BANK_BRANCH_NAME)), 
						"%"+key+"%"
					)
				)
			));
		
		List<BankBranch> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<BankBranch> getUndeletedBranchByCode(Integer bankId, Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<BankBranch> criteria = builder.createQuery(type);
		Root<BankBranch> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(BankConstants.FIELD_BANK)
							.get(BankConstants.FIELD_BANK_ID), bankId),
					builder.equal(root.get(BankConstants.FIELD_BANK_BRANCH_CODE), code), 
					builder.equal(root.get(BankConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<BankBranch> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

}