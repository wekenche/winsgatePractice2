package w.fujiko.dao.repo.transactions.purchaseorders.histories;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.transactions.purchaseorders.histories.PurchaseOrderHeaderHistoryDao;
import w.fujiko.model.transactions.purchaseorders.histories.PurchaseOrderHeaderHistory;

@Repository
@Transactional
public class PurchaseOrderHeaderHistoryDaoRepo 
	extends UniversalCrudRepo<PurchaseOrderHeaderHistory, Integer> 
	implements PurchaseOrderHeaderHistoryDao {
	
	public PurchaseOrderHeaderHistoryDaoRepo() {
		super();
		type = PurchaseOrderHeaderHistory.class;
	}

}