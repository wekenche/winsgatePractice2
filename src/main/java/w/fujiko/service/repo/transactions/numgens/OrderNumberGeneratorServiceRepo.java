package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.OrderNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.OrderNumberGenerator;
import w.fujiko.service.transactions.numgens.OrderNumberGeneratorService;

@Service
@Transactional
public class OrderNumberGeneratorServiceRepo extends UniversalServiceRepo<OrderNumberGenerator, OrderNumberGeneratorDao, Integer> implements OrderNumberGeneratorService {}
