/**
 * 
 */
package w.fujiko.service.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.GenericClassificationDao;
import w.fujiko.model.masters.GenericClassification;
import w.fujiko.service.GenericClassificationService;

/**
 * @author Yagami
 *
 */
@Service
@Transactional
public class GenericClassificationServiceRepo extends UniversalServiceRepo<GenericClassification, GenericClassificationDao, Integer>
		implements GenericClassificationService {

	@Override
	public Optional<GenericClassification> getUndeletedItemByCode(String code) {
		return dao.getUndeletedItemByCode(code);
	}
}
