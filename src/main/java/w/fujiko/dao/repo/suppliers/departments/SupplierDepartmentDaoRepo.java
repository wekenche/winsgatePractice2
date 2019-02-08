package w.fujiko.dao.repo.suppliers.departments;

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

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.suppliers.departments.SupplierDepartmentDao;
import w.fujiko.model.masters.suppliers.SupplierDepartment;

@Repository
@Transactional
public class SupplierDepartmentDaoRepo 
	extends UniversalCrudRepo<SupplierDepartment, Integer> 
	implements SupplierDepartmentDao {
	
	public SupplierDepartmentDaoRepo() {
		super();
		type = SupplierDepartment.class;
    }

	@Override
	public List<SupplierDepartment> getByBaseId(Integer baseId) {
		Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SupplierDepartment> criteria = builder.createQuery(type);
        Root<SupplierDepartment> root =  criteria.from(type);
        try{
            criteria.select(root)
                    .where(builder.equal(root.get("base").get("id"), baseId));
                    
            return session.createQuery(criteria).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
	}

	@Override
	public Optional<SupplierDepartment> getSupplierDepartmentByCode(Integer baseId, Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SupplierDepartment> criteria = builder.createQuery(type);
		Root<SupplierDepartment> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("base").get("id"), baseId),
					builder.equal(root.get("code"), code)
				)
			);
		
		Optional<SupplierDepartment> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

}