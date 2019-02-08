/**
 * 
 */
package fte.api;


import javax.validation.ConstraintViolation;

/**
 * @author Try-Parser
 *
 */
public class Errors implements Response {
	
	private String defaultMessage;
	
	private String objectName;
	
	private String field;
	
	private String rejectedValue;
	
	private Boolean bindingFailure;
	
	private String code;
	
	
	public Errors builder(ConstraintViolation<?> o) {
		String rootBeanClass = o.getRootBeanClass().toString();
				
		this.setDefaultMessage(o.getMessage());
		this.setField(o.getPropertyPath().toString());
		this.setObjectName(rootBeanClass.split("\\.", 4)[3]);
		this.setBindingFailure(true);
		this.setCode(o.getConstraintDescriptor().getAnnotation().toString());
		return this;
	}

	/* (non-Javadoc)
	 * @see fte.api.Response#success()
	 */
	public Boolean success() {
		return false;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public Boolean getBindingFailure() {
		return bindingFailure;
	}

	public void setBindingFailure(Boolean bindingFailure) {
		this.bindingFailure = bindingFailure;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
}
