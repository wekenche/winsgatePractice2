package w.fujiko.model.wrappers;

import java.util.List;

import javax.validation.constraints.NotNull;

import w.fujiko.dto.sales.details.SalesDetailCreateDTO;
import w.fujiko.dto.sales.headers.SalesHeaderCreateDTO;

public class SalesInputWrapper {
	
	@NotNull
	private SalesHeaderCreateDTO header;

	@NotNull
	private List<@NotNull SalesDetailCreateDTO> details;

	public SalesHeaderCreateDTO getHeader() {
		return header;
	}

	public void setHeader(SalesHeaderCreateDTO header) {
		this.header = header;
	}

	public List<SalesDetailCreateDTO> getDetails() {
		return details;
	}

	public void setDetails(List<SalesDetailCreateDTO> details) {
		this.details = details;
	}

}