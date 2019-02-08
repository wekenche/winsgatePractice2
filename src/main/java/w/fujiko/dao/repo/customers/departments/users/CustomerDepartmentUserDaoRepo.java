package w.fujiko.dao.repo.customers.departments.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;
import w.fujiko.dao.customers.departments.users.CustomerDepartmentUserDao;
import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class CustomerDepartmentUserDaoRepo 
	extends UniversalCrudRepo<CustomerDepartmentUser,Integer>
	implements CustomerDepartmentUserDao {

		public CustomerDepartmentUserDaoRepo() {
			super();
			type = CustomerDepartmentUser.class;
        }
        
        @Override
		public Page<CustomerDepartmentUser> paginate(int first, int max) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerDepartmentUser> criteria = builder.createQuery(type);
			Root<CustomerDepartmentUser> root =  criteria.from(type);
			criteria.select(root);

			Page<CustomerDepartmentUser> page = new Page<CustomerDepartmentUser>(); 
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
    public List<CustomerDepartmentUser> getByDepartmentId(int departmentId) {
        Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerDepartmentUser> criteria = builder.createQuery(type);
        Root<CustomerDepartmentUser> root =  criteria.from(type);
        try{
            criteria.select(root)
                    .where(builder.equal(root.get("customerDepartment").get("id"), departmentId));
                    
            return session.createQuery(criteria).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
	}
}
