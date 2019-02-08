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
import w.fujiko.dao.suppliers.SupplierGroupDao;
import w.fujiko.model.masters.suppliers.SupplierBase;
import w.fujiko.model.masters.suppliers.SupplierBase_;
import w.fujiko.model.masters.suppliers.SupplierDepartment;
import w.fujiko.model.masters.suppliers.SupplierDepartment_;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser_;
import w.fujiko.model.masters.suppliers.SupplierGroup;
import w.fujiko.model.masters.suppliers.SupplierGroup_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.util.common.constants.SupplierConstants;

@Repository
@Transactional
public class SupplierGroupDaoRepo 
	extends UniversalCrudRepo<SupplierGroup,Integer> 
	implements SupplierGroupDao {
	
	public SupplierGroupDaoRepo() {
		super();
		type = SupplierGroup.class;
    }

	@Override
	public Optional<SupplierGroup> getUndeletedSupplierGroupByCode(String code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SupplierGroup> criteria = builder.createQuery(type);
		Root<SupplierGroup> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(SupplierConstants.FIELD_CODE), code), 
					builder.equal(root.get(SupplierConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<SupplierGroup> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public List<SupplierGroup> getByUserId(Integer userId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SupplierGroup> criteria = builder.createQuery(type);
		Root<SupplierGroup> root = criteria.from(type);
		Join<SupplierGroup, SupplierBase> joinSupplierBase = root.join(SupplierGroup_.supplierBases);
		Join<SupplierBase, SupplierDepartment> joinSupplierDepartment = joinSupplierBase.join(SupplierBase_.supplierDepartments);
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

}