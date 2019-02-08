/**
 * 
 */
package w.fujiko.service.repo.customers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.customers.departments.CustomerDepartmentDao;
import w.fujiko.model.masters.customers.CustomerDepartment;
import w.fujiko.service.customers.departments.CustomerDepartmentService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class CustomerDepartmentServiceRepo extends
        UniversalServiceRepo<CustomerDepartment, CustomerDepartmentDao, Integer> implements CustomerDepartmentService {

    @Override
    public List<CustomerDepartment> getByBaseId(int baseId) {
		return dao.getByBaseId(baseId);
    }

	@Override
	public Optional<CustomerDepartment> getCustomerDepartmentByCode(Integer baseId, Integer code) {
		return dao.getCustomerDepartmentByCode(baseId, code); 
	}
}
