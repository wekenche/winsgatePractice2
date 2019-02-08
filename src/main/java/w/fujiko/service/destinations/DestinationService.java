/**
 * 
 */
package w.fujiko.service.destinations;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.destinations.Destination;

/**
 * @author Try-Parser
 *
 */
public interface DestinationService extends UniversalCrud<Destination,Integer> {
    public Optional<Destination> getUndeletedDestinationByCode(Integer code);
    List<Destination> getByCustomerCode(String customerCode);
}
