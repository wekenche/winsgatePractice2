package w.fujiko.dao.repo.infos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import w.fujiko.model.infos.CustomerCompanyFile;
import w.fujiko.model.infos.CustomerCompanyFile_;
import w.fujiko.dao.infos.CustomerCompanyFileDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class CustomerCompanyFileDaoRepo
	extends UniversalCrudRepo<CustomerCompanyFile ,Integer>
	implements CustomerCompanyFileDao {

		public CustomerCompanyFileDaoRepo() {
		super();
		type = CustomerCompanyFile.class;
	}

	@Override
	public Optional<CustomerCompanyFile> findByDocumentDate(Date documentDate) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CustomerCompanyFile> criteria = builder.createQuery(type);
		Root<CustomerCompanyFile> root = criteria.from(type);

		criteria
		.select(root)
		.where(builder.equal(root.get(CustomerCompanyFile_.date), documentDate));

		try{
			return Optional.of(session.createQuery(criteria).getSingleResult());	
		}catch(Exception ex){
			return Optional.empty();	
		}finally{
			session.close();
		}
	}

	/**
	 * Check if document date already exist for a particular customerGroup
	 * @param documentDate
	 * @param id
	 * @return
	 */
	public Boolean isUniqueDocumentDate(Date documentDate,int customerGroupId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CustomerCompanyFile> criteria = builder.createQuery(type);
		Root<CustomerCompanyFile> root = criteria.from(type);

		criteria
		.select(root)
		.where(builder.and(builder.equal(root.get(CustomerCompanyFile_.date),documentDate),
						   builder.equal(root.get("customerGroup").get("id"), customerGroupId)));

		try{
			return session.createQuery(criteria).getResultList().isEmpty();
		}catch(Exception ex){
			return false;
		}finally{
			session.close();
		}
	}

	@Override
	public List<CustomerCompanyFile> findByCustomerGroupId(Integer customerGroupId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CustomerCompanyFile> criteria = builder.createQuery(type);
		Root<CustomerCompanyFile> root = criteria.from(type);

		criteria
		.select(root)
		.where(builder.equal(root.get("customerGroup").get("id"), customerGroupId));

		try{
			return session.createQuery(criteria).getResultList();	
		}catch(Exception ex){
			return new ArrayList<CustomerCompanyFile>();
		}finally{
			session.close();
		}
	}
}
