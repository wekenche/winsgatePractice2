package w.fujiko.dao.customers;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.wrappers.CustomerBaseSearchWrapper;

public interface CustomerBaseDao extends UniversalCrud<CustomerBase,Integer> {
	public Optional<CustomerBase> getUndeletedCustomerByCode(Integer groupId, String code);
	List<CustomerBase> getByUserId(Integer userId);
	Optional<CustomerBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd);
	List<CustomerBaseSearchWrapper> search(Optional<String> groupCode,
												  Optional<String> baseCode,
												  Optional<String> customerName,
												  Optional<Short> userCode);
}