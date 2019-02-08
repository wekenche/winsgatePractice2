package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.PaymentNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.PaymentNumberGenerator;
import w.fujiko.service.transactions.numgens.PaymentNumberGeneratorService;

@Service
@Transactional
public class PaymentNumberGeneratorServiceRepo extends UniversalServiceRepo<PaymentNumberGenerator, PaymentNumberGeneratorDao, Integer> implements PaymentNumberGeneratorService {}
