/**
 * 
 */
package w.fujiko.service.repo.customers;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.customers.CustomerGroupDao;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.service.customers.CustomerGroupService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class CustomerGroupServiceRepo 
		extends UniversalServiceRepo<CustomerGroup, CustomerGroupDao, Integer> 
		implements CustomerGroupService {

	@Override
	public Optional<CustomerGroup> getUndeletedGroupByCode(String code) {
		return dao.getUndeletedGroupByCode(code);
	}

	@Override
	public List<CustomerGroup> getByUserId(Integer userId) {
		return dao.getByUserId(userId);
	}

}
