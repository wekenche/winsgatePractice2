package w.fujiko.dao.repo.suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.suppliers.SupplierBaseDao;
import w.fujiko.model.masters.suppliers.SupplierBase;
import w.fujiko.model.masters.suppliers.SupplierBase_;

import w.fujiko.model.masters.suppliers.SupplierDepartment;
import w.fujiko.model.masters.suppliers.SupplierDepartment_;
import w.fujiko.model.masters.suppliers.SupplierGroup;
import w.fujiko.model.masters.suppliers.SupplierGroup_;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser_;

import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;


@Repository
@Transactional
public class SupplierBaseDaoRepo 
	extends UniversalCrudRepo<SupplierBase,Integer>
	implements SupplierBaseDao {
	
	public SupplierBaseDaoRepo() {
		super();
		type = SupplierBase.class;
    }

	@Override
	public Optional<SupplierBase> getUndeletedSupplierBaseByCode(Integer groupId, String code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SupplierBase> criteria = builder.createQuery(type);
		Root<SupplierBase> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("group").get("id"), groupId),
					builder.equal(root.get("code"), code), 
					builder.equal(root.get("isEnd"), false)
				)
			);
		
		Optional<SupplierBase> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public List<SupplierBase> getByUserId(Integer userId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SupplierBase> criteria = builder.createQuery(SupplierBase.class);
		Root<SupplierBase> root = criteria.from(SupplierBase.class);
		Join<SupplierBase, SupplierDepartment> joinSupplierDepartment = root.join(SupplierBase_.supplierDepartments);
		Join<SupplierDepartment, SupplierDepartmentUser> joinSupplierDepartmentUser = joinSupplierDepartment.join(SupplierDepartment_.supplierDepartmentUsers);
		Join<SupplierDepartmentUser, User> joinUser = joinSupplierDepartmentUser.join(SupplierDepartmentUser_.user);
		
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
	public Optional<SupplierBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SupplierBase> criteria = builder.createQuery(SupplierBase.class);
		Root<SupplierBase> root = criteria.from(SupplierBase.class);
		Join<SupplierBase,SupplierGroup> joinGroup = root.join(SupplierBase_.group);
		
		var predicate = builder.equal(root.get(SupplierBase_.code), code);

		if(groupCode.isPresent())
			predicate = builder.and(predicate,builder.equal(joinGroup.get(SupplierGroup_.code), groupCode.get()));	

		if(isEnd.isPresent())
			predicate = builder.and(predicate,builder.equal(root.get(SupplierBase_.isEnd), isEnd.get()));			

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

}