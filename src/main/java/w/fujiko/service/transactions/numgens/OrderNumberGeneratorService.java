package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.OrderNumberGenerator;

@Service
public interface OrderNumberGeneratorService extends UniversalCrud<OrderNumberGenerator, Integer> {}
