package w.fujiko.dto.quotations;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.quotations.headers.QuotationHeaderDTO;
import w.fujiko.dto.quotations.details.QuotationDetailDTO;

public class QuotationDTO implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
    
    @JsonProperty("header")
    private QuotationHeaderDTO quotationHeader;

    @JsonProperty("details")
    private List<QuotationDetailDTO> quotationDetails;
	
    
	public QuotationHeaderDTO getQuotationHeader() {
		return quotationHeader;
	}

	public void setQuotationHeader(QuotationHeaderDTO quotationHeader) {
		this.quotationHeader = quotationHeader;
    }
    
    public List<QuotationDetailDTO> getQuotationDetails() {
		return quotationDetails;
	}

	public void setQuotationDetails(List<QuotationDetailDTO> quotationDetails) {
		this.quotationDetails = quotationDetails;
	}

}