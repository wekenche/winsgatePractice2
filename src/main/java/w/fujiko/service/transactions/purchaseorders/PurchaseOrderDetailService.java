package w.fujiko.service.transactions.purchaseorders;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetailPk;

@Service
public interface PurchaseOrderDetailService extends UniversalCrud<PurchaseOrderDetail, PurchaseOrderDetailPk> {
	public List<PurchaseOrderDetail> getByPurchaseOrderHeaderId(Integer purchaseOrderHeaderId);
}