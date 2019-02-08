package w.fujiko.model.masters.products;

import java.io.Serializable;

import fte.api.Response;

public class CSVUploadErrorResponse<T> implements Serializable, Response {
	
	private static final long serialVersionUID = -6088813284755515975L;
	private Boolean success = false;
	private T errors;
	private String csvErrorFileLink;

	public CSVUploadErrorResponse() {}
	
	public CSVUploadErrorResponse(T errors) {
		super();
		this.errors = errors;
		this.csvErrorFileLink = "";
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

	public T getErrors() {
		return errors;
	}

	public void setErrors(T errors) {
		this.errors = errors;
	}

	public String getCsvErrorFileLink() {
		return csvErrorFileLink;
	}

	public void setCsvErrorFileLink(String csvErrorFileLink) {
		this.csvErrorFileLink = csvErrorFileLink;
	}

}