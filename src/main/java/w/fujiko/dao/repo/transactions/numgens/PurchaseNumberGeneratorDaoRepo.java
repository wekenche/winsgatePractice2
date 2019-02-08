package w.fujiko.dao.repo.transactions.numgens;

/**
 * @author yagami
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.PurchaseNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.PurchaseNumberGenerator;

@Repository
@Transactional
public class PurchaseNumberGeneratorDaoRepo extends UniversalCrudRepo<PurchaseNumberGenerator, Integer> implements PurchaseNumberGeneratorDao {}
