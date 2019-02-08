/**
 * 
 */
package w.fujiko.service.repo.customers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.customers.departments.users.CustomerDepartmentUserDao;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;
import w.fujiko.service.customers.departments.users.CustomerDepartmentUserService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class CustomerDepartmentUserServiceRepo extends
        UniversalServiceRepo<CustomerDepartmentUser, CustomerDepartmentUserDao, Integer> implements CustomerDepartmentUserService {

    @Override
    public List<CustomerDepartmentUser> getByDepartmentId(int departmentId) {
        return dao.getByDepartmentId(departmentId);
    }
}
