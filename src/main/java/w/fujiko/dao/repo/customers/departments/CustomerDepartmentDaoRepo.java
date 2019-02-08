package w.fujiko.dao.repo.customers.departments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.customers.CustomerDepartment;
import w.fujiko.dao.customers.departments.CustomerDepartmentDao;
import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class CustomerDepartmentDaoRepo 
	extends UniversalCrudRepo<CustomerDepartment,Integer>
	implements CustomerDepartmentDao {

		public CustomerDepartmentDaoRepo() {
			super();
			type = CustomerDepartment.class;
        }
        
        @Override
		public Page<CustomerDepartment> paginate(int first, int max) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerDepartment> criteria = builder.createQuery(type);
			Root<CustomerDepartment> root =  criteria.from(type);
			criteria.select(root);

			Page<CustomerDepartment> page = new Page<CustomerDepartment>(); 
			CriteriaQuery<Long> total = builder.createQuery(Long.class);
			total.select(builder.count(total.from(type)));

			page.setSuccess(true);
			page.setFirst(first);
			page.setMax(max);
			page.setResults(session
							.createQuery(criteria)
							.setFirstResult(first)
							.setMaxResults(max)
							.getResultList());

			page.setTotal(session.createQuery(total).getSingleResult());
			session.close();
        return page;
    }

    @Override
    public List<CustomerDepartment> getByBaseId(int baseId) {
        Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerDepartment> criteria = builder.createQuery(type);
        Root<CustomerDepartment> root =  criteria.from(type);
        try{
            criteria.select(root)
                    .where(builder.equal(root.get("base").get("id"), baseId));
                    
            return session.createQuery(criteria).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
	}

	@Override
	public Optional<CustomerDepartment> getCustomerDepartmentByCode(Integer baseId, Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CustomerDepartment> criteria = builder.createQuery(type);
		Root<CustomerDepartment> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("base").get("id"), baseId),
					builder.equal(root.get("code"), code)
				)
			);
		
		Optional<CustomerDepartment> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}
}
