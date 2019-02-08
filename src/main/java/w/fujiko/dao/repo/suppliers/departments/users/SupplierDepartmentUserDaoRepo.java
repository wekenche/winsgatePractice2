package w.fujiko.dao.repo.suppliers.departments.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.suppliers.departments.users.SupplierDepartmentUserDao;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;

@Repository
@Transactional
public class SupplierDepartmentUserDaoRepo 
	extends UniversalCrudRepo<SupplierDepartmentUser,Integer> 
	implements SupplierDepartmentUserDao {
	
	public SupplierDepartmentUserDaoRepo() {
		super();
		type = SupplierDepartmentUser.class;
    }

	@Override
	public List<SupplierDepartmentUser> getByDepartmentId(Integer departmentId) {
		Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SupplierDepartmentUser> criteria = builder.createQuery(type);
        Root<SupplierDepartmentUser> root =  criteria.from(type);
        try{
            criteria.select(root)
                    .where(builder.equal(root.get("supplierDepartment").get("id"), departmentId));
                    
            return session.createQuery(criteria).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
	}

}