package w.fujiko.service.repo.transactions.purchaseorders.histories;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.purchaseorders.histories.PurchaseOrderHeaderHistoryDao;
import w.fujiko.model.transactions.purchaseorders.histories.PurchaseOrderHeaderHistory;
import w.fujiko.service.transactions.purchaseorders.histories.PurchaseOrderHeaderHistoryService;

@Service
@Transactional
public class PurchaseOrderHeaderHistoryServiceRepo 
	extends UniversalServiceRepo<PurchaseOrderHeaderHistory, PurchaseOrderHeaderHistoryDao, Integer> 
	implements PurchaseOrderHeaderHistoryService {

}
