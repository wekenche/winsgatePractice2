package w.fujiko.dao.customers.departments;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerDepartment;

public interface CustomerDepartmentDao extends UniversalCrud<CustomerDepartment,Integer> {
    public List<CustomerDepartment> getByBaseId(int baseId);
    public Optional<CustomerDepartment> getCustomerDepartmentByCode(Integer baseId, Integer code);
}