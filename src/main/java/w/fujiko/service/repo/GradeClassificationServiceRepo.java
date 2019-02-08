/**
 * 
 */
package w.fujiko.service.repo;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.GradeClassificationDao;
import w.fujiko.model.masters.GradeClassification;
import w.fujiko.service.GradeClassificationService;

/**
 * @author Yagami
 *
 */
@Service
@Transactional
public class GradeClassificationServiceRepo extends UniversalServiceRepo<GradeClassification, GradeClassificationDao, Short>
		implements GradeClassificationService {

	@Override
	public Optional<GradeClassification> getUndeletedItemByCode(short code,int makerId){
		return dao.getUndeletedItemByCode(code,makerId);
    }

    @Override
	public List<GradeClassification> getByMakerId(int makerId){
		return dao.getByMakerId(makerId);
    }
}
