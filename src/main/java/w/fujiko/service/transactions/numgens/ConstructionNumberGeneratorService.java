package w.fujiko.service.transactions.numgens;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator;

@Service
public interface ConstructionNumberGeneratorService extends UniversalCrud<ConstructionNumberGenerator, User> {
    public Optional<ConstructionNumberGenerator> getByUserId(Integer userId);
}
