/**
 * 
 */
package fte.api;

/**
 * @author Try-Parser
 *
 */
public class Success implements Response {
	private Boolean success = true;
	
	public Success(){}

	public Success(Boolean isSuccess){
		this.success=isSuccess;
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
}
