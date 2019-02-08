package w.fujiko.dao.repo.departments;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.departments.DepartmentDao;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.departments.Department_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;

@Repository
@Transactional
public class DepartmentDaoRepo 
	extends UniversalCrudRepo<Department ,Integer>
	implements DepartmentDao {

	public DepartmentDaoRepo() {
		super();
		type = Department.class;
	}

	public void softDeleteDepartmentsById(List<Integer> departmentIds) throws ConstraintViolationException{
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();			
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Department> criteriaUpdate = builder.createCriteriaUpdate(Department.class);
		Root<Department> root = criteriaUpdate.from(Department.class);

		Expression<Integer> departmentIdExpression = root.get(Department_.id);
		Predicate departmentIdPredicate = departmentIdExpression.in(departmentIds);

		transaction.begin();		
			
		criteriaUpdate
		.set(root.get(Department_.isEnd), true)
		.where(departmentIdPredicate);

		session.createQuery(criteriaUpdate).executeUpdate();

		transaction.commit();
		session.close();
	}

	@Override
	public Optional<Department> getUndeletedDepartmentByCode(Integer branchId, Integer parentDepartmentId,
		String code) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = builder.createQuery(type);
		Root<Department> root = criteria.from(type);
		
		if(parentDepartmentId != null) {
			criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("branch").get("id"), branchId),
					builder.equal(root.get("parentDepartment").get("id"), parentDepartmentId),
					builder.equal(root.get(Department_.code), code), 
					builder.equal(root.get(Department_.isEnd), false)
				)
			);
		} else {
			criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("branch").get("id"), branchId),
					builder.isNull(root.get("parentDepartment").get("id")),
					builder.equal(root.get(Department_.code), code), 
					builder.equal(root.get(Department_.isEnd), false)
				)
			);
		}
		
		Optional<Department> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public Optional<Department> getDepartmentOfUser(Integer userId) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = builder.createQuery(type);
		Root<Department> root = criteria.from(type);
		Join<Department,User> userJoined = root.join(Department_.users);
 		criteria.select(root)
				.where(
					builder.equal(
						userJoined.get(User_.id),
						userId						
					)
				);
		
		Optional<Department> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public Optional<Department> getDepartmentByCode(String code,Optional<Boolean> isEnd) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = builder.createQuery(type);
		Root<Department> root = criteria.from(type);
		var predicate = builder.equal(
							root.get(Department_.code),
							code
						);

		if(isEnd.isPresent()) {
			predicate = builder.and(
							predicate,
							builder.equal(
								root.get(Department_.isEnd),
								isEnd.get()
							)
						);
		}
		
 		criteria.select(root)
				.where(predicate);
		
		Optional<Department> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}
}
