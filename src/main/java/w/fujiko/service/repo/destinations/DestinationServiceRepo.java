/**
 * 
 */
package w.fujiko.service.repo.destinations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.destinations.DestinationDao;
import w.fujiko.model.masters.destinations.Destination;
import w.fujiko.service.destinations.DestinationService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class DestinationServiceRepo extends UniversalServiceRepo<Destination, DestinationDao, Integer>
		implements DestinationService {

	@Override
	public Optional<Destination> getUndeletedDestinationByCode(Integer code) {
		return dao.getUndeletedDestinationByCode(code);
	}

	@Override
	public List<Destination> getByCustomerCode(String customerCode) {
		return dao.getByCustomerCode(customerCode);
	}
}
