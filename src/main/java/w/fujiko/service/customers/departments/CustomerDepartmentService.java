/**
 * 
 */
package w.fujiko.service.customers.departments;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerDepartment;

/**
 * @author Yagami
 *
 */
public interface CustomerDepartmentService extends UniversalCrud<CustomerDepartment,Integer> {
    public List<CustomerDepartment> getByBaseId(int baseId);
    public Optional<CustomerDepartment> getCustomerDepartmentByCode(Integer baseId, Integer code);
}