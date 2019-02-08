/**
 * 
 */
package w.fujiko.service.repo.departments;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.departments.DepartmentDao;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.service.departments.DepartmentService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class DepartmentServiceRepo 
		extends UniversalServiceRepo<Department, DepartmentDao, Integer> implements DepartmentService {

	@Override
	public void softDeleteDepartmentsById(List<Integer> departmentIds)
			throws ConstraintViolationException {
				this.dao.softDeleteDepartmentsById(departmentIds);
	}

	@Override
	public Optional<Department> getUndeletedDepartmentByCode(Integer branchId, Integer parentDepartmentId,
		String code) {
		return dao.getUndeletedDepartmentByCode(branchId, parentDepartmentId, code);
	}

	@Override
	public Optional<Department> getDepartmentOfUser(Integer userId) {
		return dao.getDepartmentOfUser(userId);
	}

	@Override
	public Optional<Department> getDepartmentByCode(String code,Optional<Boolean> isEnd) {
		return dao.getDepartmentByCode(code,isEnd);	
	}
}