/**
 * 
 */
package w.fujiko.service.repo.customers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.customers.CustomerBaseDao;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.wrappers.CustomerBaseSearchWrapper;
import w.fujiko.service.customers.CustomerBaseService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class CustomerBaseServiceRepo extends UniversalServiceRepo<CustomerBase, CustomerBaseDao, Integer>
		implements CustomerBaseService {

	@Override
	public Optional<CustomerBase> getUndeletedCustomerByCode(Integer groupId, String code) {
		return dao.getUndeletedCustomerByCode(groupId, code);
	}

	@Override
	public List<CustomerBase> getByUserId(Integer userId) {
		return dao.getByUserId(userId);
	}

	@Override
	public Optional<CustomerBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd) {
		return dao.getByCode(code, groupCode, isEnd);
	}

	@Override
	public List<CustomerBaseSearchWrapper> search(Optional<String> groupCode, Optional<String> baseCode,
			Optional<String> customerName, Optional<Short> userCode) {
		return dao.search(groupCode, baseCode, customerName, userCode);
	}
	
}