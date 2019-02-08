package w.fujiko.dao.customers;

import java.util.Optional;
import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerGroup;

public interface CustomerGroupDao extends UniversalCrud<CustomerGroup,Integer> {
	public Optional<CustomerGroup> getUndeletedGroupByCode(String code);
	List<CustomerGroup> getByUserId(Integer userId);
}