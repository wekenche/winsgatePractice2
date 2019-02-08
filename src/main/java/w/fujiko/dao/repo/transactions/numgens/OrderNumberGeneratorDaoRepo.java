package w.fujiko.dao.repo.transactions.numgens;

/**
 * @author yagami
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.OrderNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.OrderNumberGenerator;

@Repository
@Transactional
public class OrderNumberGeneratorDaoRepo extends UniversalCrudRepo<OrderNumberGenerator, Integer> implements OrderNumberGeneratorDao {}
