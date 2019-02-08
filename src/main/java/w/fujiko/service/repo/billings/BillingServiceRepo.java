/**
 * 
 */
package w.fujiko.service.repo.billings;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.billings.BillingDao;
import w.fujiko.model.masters.billings.Billing;
import w.fujiko.service.billings.BillingService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class BillingServiceRepo 
		extends UniversalServiceRepo<Billing, BillingDao, Integer> 
		implements BillingService {

	@Override
	public Optional<Billing> getUndeletedBillingByCode(Integer code) {
		return dao.getUndeletedBillingByCode(code);
	}

}