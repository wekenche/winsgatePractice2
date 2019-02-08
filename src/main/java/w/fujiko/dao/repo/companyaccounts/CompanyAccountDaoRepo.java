package w.fujiko.dao.repo.companyaccounts;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.companyaccounts.CompanyAccountDao;
import w.fujiko.model.masters.companyaccounts.CompanyAccount;
import w.fujiko.util.common.constants.CompanyAccountConstants;

@Repository
@Transactional
public class CompanyAccountDaoRepo 
	extends UniversalCrudRepo<CompanyAccount, Integer>
	implements CompanyAccountDao {
	
	public CompanyAccountDaoRepo() {
		super();
		type = CompanyAccount.class;
	}

	@Override
	public List<CompanyAccount> getCompanyAccountsRange(Integer from, Integer to) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CompanyAccount> criteria = builder.createQuery(type);
		Root<CompanyAccount> root = criteria.from(type);
		
		criteria.select(root)
			.where(builder.between(
					root.get(CompanyAccountConstants.ID), from, to));
		
		List<CompanyAccount> t = session.createQuery(criteria).getResultList();
		
		session.close();
		return t;
		
	}

}