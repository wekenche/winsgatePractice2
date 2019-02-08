package w.fujiko.dao.departments;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.departments.Department;

public interface DepartmentDao extends UniversalCrud<Department,Integer> {	
    void softDeleteDepartmentsById(List<Integer> departmentIds) throws ConstraintViolationException;
    public Optional<Department> getUndeletedDepartmentByCode
        (Integer branchId, Integer parentDepartmentId, String code);
    public Optional<Department> getDepartmentOfUser(Integer userId);
    public Optional<Department> getDepartmentByCode(String code,Optional<Boolean> isEnd);
}