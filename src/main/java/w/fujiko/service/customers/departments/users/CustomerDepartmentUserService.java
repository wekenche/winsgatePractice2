/**
 * 
 */
package w.fujiko.service.customers.departments.users;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;

/**
 * @author Yagami
 *
 */
public interface CustomerDepartmentUserService extends UniversalCrud<CustomerDepartmentUser,Integer> {
    List<CustomerDepartmentUser> getByDepartmentId(int departmentId);
}