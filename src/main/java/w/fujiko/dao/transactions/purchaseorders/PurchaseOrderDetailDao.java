package w.fujiko.dao.transactions.purchaseorders;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetailPk;

public interface PurchaseOrderDetailDao extends UniversalCrud<PurchaseOrderDetail, PurchaseOrderDetailPk> {
	public List<PurchaseOrderDetail> getByPurchaseOrderHeaderId(Integer purchaseOrderHeaderId);
	void deleteByPurchaseOrderHeaderId(Integer purchaseOrderHeaderId);
}