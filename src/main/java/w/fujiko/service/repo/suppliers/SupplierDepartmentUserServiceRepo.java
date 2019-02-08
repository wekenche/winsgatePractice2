package w.fujiko.service.repo.suppliers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.suppliers.departments.users.SupplierDepartmentUserDao;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;
import w.fujiko.service.suppliers.departments.users.SupplierDepartmentUserService;

@Service
@Transactional
public class SupplierDepartmentUserServiceRepo 
	extends UniversalServiceRepo<SupplierDepartmentUser, SupplierDepartmentUserDao, Integer> 
	implements SupplierDepartmentUserService {

	@Override
	public List<SupplierDepartmentUser> getByDepartmentId(Integer departmentId) {
		return dao.getByDepartmentId(departmentId);
	}

}