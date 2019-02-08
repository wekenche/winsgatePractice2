package w.fujiko.dao.billings;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.billings.Billing;

public interface BillingDao extends UniversalCrud<Billing,Integer> {	
	public Optional<Billing> getUndeletedBillingByCode(Integer code);
}