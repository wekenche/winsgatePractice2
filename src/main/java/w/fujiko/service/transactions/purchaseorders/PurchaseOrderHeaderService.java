package w.fujiko.service.transactions.purchaseorders;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.universal.UniversalCrud;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderSearchDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;

@Service
public interface PurchaseOrderHeaderService extends UniversalCrud<PurchaseOrderHeader, Integer> {
	public Optional<PurchaseOrderHeader> getBySlipNumber(String slipNumber);
	public Page<PurchaseOrderHeader> purchaseOrderHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys);
	public void generateReport(String slipNumber, HttpServletResponse response);
}