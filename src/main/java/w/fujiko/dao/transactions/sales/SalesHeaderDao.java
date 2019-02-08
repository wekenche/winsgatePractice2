package w.fujiko.dao.transactions.sales;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.sales.SalesHeader;

public interface SalesHeaderDao extends UniversalCrud<SalesHeader, Integer> {
	public Optional<SalesHeader> getBySlipNumber(String slipNumber);
	public void deleteById(Integer salesHeaderId);
	// public Page<SalesHeader> purchaseOrderHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys);
}