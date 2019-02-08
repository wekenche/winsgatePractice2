/**
 * 
 */
package w.fujiko.service.billings;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.billings.Billing;

/**
 * @author Try-Parser
 *
 */
public interface BillingService extends UniversalCrud<Billing,Integer> {
	public Optional<Billing> getUndeletedBillingByCode(Integer code);
}