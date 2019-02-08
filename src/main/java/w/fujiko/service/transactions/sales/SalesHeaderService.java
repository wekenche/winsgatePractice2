package w.fujiko.service.transactions.sales;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.sales.SalesHeader;

@Service
public interface SalesHeaderService extends UniversalCrud<SalesHeader, Integer> {
	public Optional<SalesHeader> getBySlipNumber(String slipNumber);
	// public Page<SalesHeader> salesHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys);
}