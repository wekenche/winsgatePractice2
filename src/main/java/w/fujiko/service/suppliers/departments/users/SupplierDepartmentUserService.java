package w.fujiko.service.suppliers.departments.users;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;

@Service
public interface SupplierDepartmentUserService 
	extends UniversalCrud<SupplierDepartmentUser, Integer> {
	
	public List<SupplierDepartmentUser> getByDepartmentId(Integer departmentId);

}