package w.fujiko.dao.repo.branches;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.branches.Branch_;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.departments.Department_;
import w.fujiko.model.masters.users.User_;
import w.fujiko.dao.branches.BranchDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class BranchDaoRepo 
	extends UniversalCrudRepo<Branch ,Integer>
	implements BranchDao {

	public BranchDaoRepo() {
		super();
		type = Branch.class;
	}

	@Override
	public Optional<Branch> getUndeletedBranchByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Branch> criteria = builder.createQuery(type);
		Root<Branch> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(Branch_.code), code), 
					builder.equal(root.get(Branch_.isEnd), false)
				)
			);
		
		Optional<Branch> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public Optional<Branch> getByCode(String code,Optional<Boolean> isEnd) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Branch> criteria = builder.createQuery(type);
		Root<Branch> root = criteria.from(type);
		var predicate = builder.equal(
							root.get(Branch_.code),
							code
						);

		if(isEnd.isPresent()) {
			predicate = builder.and(
							predicate,
							builder.equal(
								root.get(Branch_.isEnd),
								isEnd.get()
							)
						);
		}
		
 		criteria.select(root)
				.where(predicate);
		
		Optional<Branch> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public Optional<Branch> getBranchOfUser(Short userCode,Boolean isEnd) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Branch> criteria = builder.createQuery(type);
		Root<Branch> root = criteria.from(type);
		var departmentJoined = root.join(Branch_.departments);
		var userJoined = departmentJoined.join(Department_.users);

 		criteria.select(root)
				.where(builder.and(
					builder.equal(
						userJoined.get(User_.code),
						userCode
					),
					builder.equal(
						userJoined.get(User_.isResigned)
						,isEnd
					)
				)
		);
		
		Optional<Branch> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		finally{
			session.close();
		}		
		return result;
	}
}
