package w.fujiko.dao.repo.customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.customers.CustomerBaseDao;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.customers.CustomerBase_;
import w.fujiko.model.masters.customers.CustomerDepartment;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;
import w.fujiko.model.masters.customers.CustomerDepartmentUser_;
import w.fujiko.model.masters.customers.CustomerDepartment_;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.model.masters.customers.CustomerGroup_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.model.wrappers.CustomerBaseSearchWrapper;
import w.fujiko.util.common.constants.CustomerConstants;

@Repository
@Transactional
public class CustomerBaseDaoRepo 
	extends UniversalCrudRepo<CustomerBase,Integer>
	implements CustomerBaseDao {

		public CustomerBaseDaoRepo() {
			super();
			type = CustomerBase.class;
        }
        
        @Override
		public Page<CustomerBase> paginate(int first, int max) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerBase> criteria = builder.createQuery(type);
			Root<CustomerBase> root =  criteria.from(type);
			criteria.select(root);

			Page<CustomerBase> page = new Page<CustomerBase>(); 
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
		public Optional<CustomerBase> getUndeletedCustomerByCode(Integer groupId, String code) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerBase> criteria = builder.createQuery(type);
			Root<CustomerBase> root = criteria.from(type);
			
			criteria.select(root)
				.where(
					builder.and(
						builder.equal(root.get(CustomerConstants.FIELD_GROUP)
								.get(CustomerConstants.FIELD_ID), groupId),
						builder.equal(root.get(CustomerConstants.FIELD_CODE), code), 
						builder.equal(root.get(CustomerConstants.FIELD_IS_END), false)
					)
				);
			
			Optional<CustomerBase> result;
			
			try {
				result = Optional.of(session.createQuery(criteria).getSingleResult());
			} catch(NoResultException nre) {
				result = Optional.empty();
			}
			session.close();
			
			return result;
		}

		@Override
		public List<CustomerBase> getByUserId(Integer userId) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerBase> criteria = builder.createQuery(CustomerBase.class);
			Root<CustomerBase> root = criteria.from(CustomerBase.class);
			Join<CustomerBase,CustomerDepartment> joinCustomerDepartment = root.join(CustomerBase_.customerDepartments);
			Join<CustomerDepartment,CustomerDepartmentUser> joinCustomerDepartmentUser = joinCustomerDepartment.join(CustomerDepartment_.customerDepartmentUsers);
			Join<CustomerDepartmentUser, User> joinUser = joinCustomerDepartmentUser.join(CustomerDepartmentUser_.user);
			
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

		@Override
		public Optional<CustomerBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerBase> criteria = builder.createQuery(CustomerBase.class);
			Root<CustomerBase> root = criteria.from(CustomerBase.class);
			Join<CustomerBase,CustomerGroup> joinGroup = root.join(CustomerBase_.group);
			
			var predicate = builder.equal(root.get(CustomerBase_.code), code);

			if(groupCode.isPresent())
				predicate = builder.and(predicate,builder.equal(joinGroup.get(CustomerGroup_.code), groupCode.get()));	

			if(isEnd.isPresent())
				predicate = builder.and(predicate,builder.equal(root.get(CustomerBase_.isEnd), isEnd.get()));			

			criteria.select(root)					
					.where(predicate);			
			
			try {
				return Optional.of(session
								   .createQuery(criteria)
								   .setFirstResult(0)
								   .setMaxResults(1)
								   .getSingleResult());
			}catch(Exception ex){
				return Optional.empty();
			}
			finally{
				session.close();
			}			
		}

	@Override
	public List<CustomerBaseSearchWrapper> search(Optional<String> groupCode,
												  Optional<String> baseCode,
												  Optional<String> customerName,
												  Optional<Short> userCode) {

		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CustomerBaseSearchWrapper> criteria = builder.createQuery(CustomerBaseSearchWrapper.class);
		Root<CustomerBase> root =  criteria.from(type);
		var joinCustomerGroup =  root.join(CustomerBase_.group);
		var joinCustomerDepartment =  root.join(CustomerBase_.customerDepartments,JoinType.LEFT);
		var joinDepartmentUser =  joinCustomerDepartment.join(CustomerDepartment_.customerDepartmentUsers,JoinType.LEFT);
		var joinUser =  joinDepartmentUser.join(CustomerDepartmentUser_.user,JoinType.LEFT);
		
		var predicate = builder.conjunction();
		
		if(groupCode.isPresent()){
			predicate = builder.and(
							predicate,
							builder.equal(
								joinCustomerGroup.get(CustomerGroup_.code),
								groupCode.get().trim()
							)
						);
		}
		
		if(customerName.isPresent()){
			predicate = builder.and(
							predicate,
							builder.like(
								builder.lower(
									builder.concat(
										builder.concat(
											joinCustomerGroup.get(CustomerGroup_.name),
											" "
										),
										root.get(CustomerBase_.name)
									)
								),
								"%"+customerName.get().trim()+"%"
							)
						);
		}

		if(baseCode.isPresent()){
			predicate = builder.and(
							predicate,
							builder.equal(
								root.get(CustomerBase_.code),
								baseCode.get().trim()
							)
						);
		}

		if(userCode.isPresent()){
			predicate = builder.and(
							predicate,
							builder.equal(
								joinUser.get(User_.code),
								userCode.get()
							)
						);
		}

		var selection = builder.construct(CustomerBaseSearchWrapper.class,
											root.get(CustomerBase_.id), 
											root.get(CustomerBase_.code),
											root.get(CustomerBase_.group),
											root.get(CustomerBase_.name),
											joinCustomerDepartment,
											joinDepartmentUser);

		try{
			criteria
			.select(selection)
			.where(predicate);			
			return session.createQuery(criteria).getResultList();
		}catch(Exception ex){
			return new ArrayList<>();
		}
	}

}
