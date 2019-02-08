package w.fujiko.dao.transactions.purchaseorders;

import java.util.Optional;

import fte.api.Page;
import fte.api.universal.UniversalCrud;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderSearchDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;

public interface PurchaseOrderHeaderDao extends UniversalCrud<PurchaseOrderHeader, Integer> {
	public Optional<PurchaseOrderHeader> getBySlipNumber(String slipNumber);
	public void deleteById(Integer purchaseOrderHeaderId);
	public Page<PurchaseOrderHeader> purchaseOrderHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys);
}