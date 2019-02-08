package w.fujiko.dao.customers.departments.users;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;

public interface CustomerDepartmentUserDao extends UniversalCrud<CustomerDepartmentUser,Integer> {
    List<CustomerDepartmentUser> getByDepartmentId(int departmentId);
}