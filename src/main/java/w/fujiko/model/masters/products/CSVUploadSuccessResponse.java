package w.fujiko.model.masters.products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fte.api.Response;

public class CSVUploadSuccessResponse implements Serializable, Response {

	private static final long serialVersionUID = -2635166657063652306L;
	private Boolean success = true;
	private List<String> info;
	
	public CSVUploadSuccessResponse() {
		this.info = new ArrayList<>();
	}
	
	public CSVUploadSuccessResponse(List<String> info) {
		super();
		this.info = info;
	}

	@Override
	public Boolean success() {
		return this.success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<String> getInfo() {
		return info;
	}

	public void setInfo(List<String> info) {
		this.info = info;
	}
	
}