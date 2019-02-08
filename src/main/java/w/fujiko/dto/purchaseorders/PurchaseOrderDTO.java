package w.fujiko.dto.purchaseorders;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.purchaseorders.details.PurchaseOrderDetailDTO;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderDTO;

public class PurchaseOrderDTO implements Serializable {

	private static final long serialVersionUID = -3412029397139377063L;
	
	@JsonProperty("header")
	private PurchaseOrderHeaderDTO purchaseOrderHeader;

	@JsonProperty("details")
	private List<PurchaseOrderDetailDTO> purchaseOrderDetails;

	public PurchaseOrderHeaderDTO getPurchaseOrderHeader() {
		return purchaseOrderHeader;
	}

	public void setPurchaseOrderHeader(PurchaseOrderHeaderDTO purchaseOrderHeader) {
		this.purchaseOrderHeader = purchaseOrderHeader;
	}

	public List<PurchaseOrderDetailDTO> getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	public void setPurchaseOrderDetails(List<PurchaseOrderDetailDTO> purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}

}