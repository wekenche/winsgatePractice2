package w.fujiko.service.transactions.purchaseorders.histories;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.purchaseorders.histories.PurchaseOrderHeaderHistory;

@Service
public interface PurchaseOrderHeaderHistoryService extends UniversalCrud<PurchaseOrderHeaderHistory, Integer> {

}
