package w.fujiko.model.wrappers;

import java.util.List;

import javax.validation.constraints.NotNull;

import w.fujiko.dto.purchaseorders.details.PurchaseOrderDetailCreateDTO;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderCreateDTO;

public class PurchaseOrderInputWrapper {
	
	@NotNull
	private PurchaseOrderHeaderCreateDTO header;

	@NotNull
	private List<@NotNull PurchaseOrderDetailCreateDTO> details;

	public PurchaseOrderHeaderCreateDTO getHeader() {
		return header;
	}

	public void setHeader(PurchaseOrderHeaderCreateDTO header) {
		this.header = header;
	}

	public List<PurchaseOrderDetailCreateDTO> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseOrderDetailCreateDTO> details) {
		this.details = details;
	}

}