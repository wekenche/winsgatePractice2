package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.SalesNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.SalesNumberGenerator;
import w.fujiko.service.transactions.numgens.SalesNumberGeneratorService;

@Service
@Transactional
public class SalesNumberGeneratorServiceRepo extends UniversalServiceRepo<SalesNumberGenerator, SalesNumberGeneratorDao, Integer> implements SalesNumberGeneratorService {}
