package w.fujiko.service.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.RatioClassificationDao;
import w.fujiko.model.masters.RatioClassification;
import w.fujiko.service.RatioClassificationService;

@Service
@Transactional
public class RatioClassificationServiceRepo 
	extends UniversalServiceRepo<RatioClassification, RatioClassificationDao, Integer> 
	implements RatioClassificationService {

	@Override
	public Optional<RatioClassification> getUndeletedItemByCode(Integer code, Integer makerId) {
		return dao.getUndeletedItemByCode(code, makerId);
	}

}
