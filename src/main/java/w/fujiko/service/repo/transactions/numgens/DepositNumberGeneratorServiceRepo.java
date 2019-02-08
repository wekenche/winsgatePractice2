package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.DepositNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.DepositNumberGenerator;
import w.fujiko.service.transactions.numgens.DepositNumberGeneratorService;

@Service
@Transactional
public class DepositNumberGeneratorServiceRepo extends UniversalServiceRepo<DepositNumberGenerator, DepositNumberGeneratorDao, Integer> implements DepositNumberGeneratorService {}
