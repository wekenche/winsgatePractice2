package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.WorkingSiteNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.WorkingSiteNumberGenerator;
import w.fujiko.service.transactions.numgens.WorkingSiteNumberGeneratorService;

@Service
@Transactional
public class WorkingSiteNumberGeneratorServiceRepo extends UniversalServiceRepo<WorkingSiteNumberGenerator, WorkingSiteNumberGeneratorDao, Integer> implements WorkingSiteNumberGeneratorService {

    @Override
    public void deleteByUserID(Integer userId) {
        dao.deleteByUserID(userId);
    }
    
}
