package w.fujiko.dto.sales;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.sales.details.SalesDetailDTO;
import w.fujiko.dto.sales.headers.SalesHeaderDTO;

public class SalesDTO implements Serializable {

	private static final long serialVersionUID = -3412029397139377063L;
	
	@JsonProperty("header")
	private SalesHeaderDTO salesHeader;

	@JsonProperty("details")
	private List<SalesDetailDTO> salesDetails;

	public SalesHeaderDTO getSalesHeader() {
		return salesHeader;
	}

	public void setSalesHeader(SalesHeaderDTO salesHeader) {
		this.salesHeader = salesHeader;
	}

	public List<SalesDetailDTO> getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(List<SalesDetailDTO> salesDetails) {
		this.salesDetails = salesDetails;
	}
}