package w.fujiko.service.repo.transactions.numgens;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.ConstructionNumberGeneratorDao;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator;
import w.fujiko.service.transactions.numgens.ConstructionNumberGeneratorService;

@Service
@Transactional
public class ConstructionNumberGeneratorServiceRepo
        extends UniversalServiceRepo<ConstructionNumberGenerator, ConstructionNumberGeneratorDao, User>
        implements ConstructionNumberGeneratorService {

    @Override
    public Optional<ConstructionNumberGenerator> getByUserId(Integer userId) {
		  return dao.getByUserId(userId);
    }
}
