package w.fujiko.dao.repo.customers;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.customers.CustomerGroupDao;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.model.masters.customers.CustomerGroup_;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.customers.CustomerBase_;
import w.fujiko.model.masters.customers.CustomerDepartment;
import w.fujiko.model.masters.customers.CustomerDepartment_;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;
import w.fujiko.model.masters.customers.CustomerDepartmentUser_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.util.common.constants.CustomerConstants;

@Repository
@Transactional
public class CustomerGroupDaoRepo 
	extends UniversalCrudRepo<CustomerGroup,Integer>
	implements CustomerGroupDao {

		public CustomerGroupDaoRepo() {
			super();
			type = CustomerGroup.class;
        }
        
        @Override
		public Page<CustomerGroup> paginate(int first, int max) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerGroup> criteria = builder.createQuery(type);
			Root<CustomerGroup> root =  criteria.from(type);
			criteria.select(root);

			Page<CustomerGroup> page = new Page<CustomerGroup>(); 
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
		public Optional<CustomerGroup> getUndeletedGroupByCode(String code) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerGroup> criteria = builder.createQuery(type);
			Root<CustomerGroup> root = criteria.from(type);
			
			criteria.select(root)
				.where(
					builder.and(
						builder.equal(root.get(CustomerConstants.FIELD_CODE), code), 
						builder.equal(root.get(CustomerConstants.FIELD_IS_END), false)
					)
				);
			
			Optional<CustomerGroup> result;
			
			try {
				result = Optional.of(session.createQuery(criteria).getSingleResult());
			} catch(NoResultException nre) {
				result = Optional.empty();
			}
			session.close();
			
			return result;
		}

		@Override
		public List<CustomerGroup> getByUserId(Integer userId) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerGroup> criteria = builder.createQuery(type);
			Root<CustomerGroup> root = criteria.from(type);
			Join<CustomerGroup,CustomerBase> joinCustomerBase = root.join(CustomerGroup_.customerBases);
			Join<CustomerBase,CustomerDepartment> joinCustomerDepartment = joinCustomerBase.join(CustomerBase_.customerDepartments);
			Join<CustomerDepartment,CustomerDepartmentUser> joinCustomerDepartmentUser = joinCustomerDepartment.join(CustomerDepartment_.customerDepartmentUsers);
			Join<CustomerDepartmentUser,User> joinUser = joinCustomerDepartmentUser.join(CustomerDepartmentUser_.user);
			
			criteria.select(root)
					.distinct(true)
					.where(
						builder.equal(joinUser.get(User_.id), userId)
					);
			
			
			try {
				return session.createQuery(criteria).getResultList();
			}catch(Exception ex){
				return new ArrayList<>();
			}
			finally{
				session.close();
			}			
		}

}
