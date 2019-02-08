package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.PaymentNumberGenerator;

@Service
public interface PaymentNumberGeneratorService extends UniversalCrud<PaymentNumberGenerator, Integer> {}
