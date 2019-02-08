/**
 * 
 */
package w.fujiko.service.customers;

import java.util.Optional;
import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.customers.CustomerGroup;

/**
 * @author Yagami
 *
 */
public interface CustomerGroupService extends UniversalCrud<CustomerGroup,Integer> {
	public Optional<CustomerGroup> getUndeletedGroupByCode(String code);
	public List<CustomerGroup> getByUserId(Integer userId);
}
