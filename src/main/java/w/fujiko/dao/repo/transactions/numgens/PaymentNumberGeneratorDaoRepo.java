package w.fujiko.dao.repo.transactions.numgens;

/**
 * @author yagami
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.PaymentNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.PaymentNumberGenerator;

@Repository
@Transactional
public class PaymentNumberGeneratorDaoRepo extends UniversalCrudRepo<PaymentNumberGenerator, Integer> implements PaymentNumberGeneratorDao {}
