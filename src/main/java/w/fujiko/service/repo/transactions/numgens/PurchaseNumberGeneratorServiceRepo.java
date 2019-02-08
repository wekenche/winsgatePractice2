package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.PurchaseNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.PurchaseNumberGenerator;
import w.fujiko.service.transactions.numgens.PurchaseNumberGeneratorService;

@Service
@Transactional
public class PurchaseNumberGeneratorServiceRepo extends UniversalServiceRepo<PurchaseNumberGenerator, PurchaseNumberGeneratorDao, Integer> implements PurchaseNumberGeneratorService {}
