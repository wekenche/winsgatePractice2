package w.fujiko.dto.sales.details;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesDetailCreateDTO implements Serializable {

	private static final long serialVersionUID = -2659621502091783793L;
	
	@JsonProperty("quotationHeaderId")
	private Integer quotationHeaderId;
	
	@JsonProperty("taskId")
	private Short taskId;

    @JsonProperty("lineNumber")
	private Short lineNumber;
    
    @JsonProperty("salesSection")
	private Byte salesSection;
	
    @JsonProperty("modelNumber")
    private String modelNumber;
	
    @JsonProperty("productName")
    private String productName;
	
    @JsonProperty("makerId")
    private Integer makerId;
	
    @JsonProperty("productId")
    private Integer productId;
	
    @JsonProperty("unitId")
    private Integer unitId;
	
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("unitPrice")
    private Double unitPrice;
	
    @JsonProperty("originalUnitPrice")
    private Integer originalUnitPrice;
    
    @JsonProperty("amount")
	private Double amount;
    
    @JsonProperty("remarks")
	private String remarks;
	
	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;

	public Integer getQuotationHeaderId() {
		return quotationHeaderId;
	}

	public void setQuotationHeaderId(Integer quotationHeaderId) {
		this.quotationHeaderId = quotationHeaderId;
	}

	public Short getTaskId() {
		return taskId;
	}

	public void setTaskId(Short taskId) {
		this.taskId = taskId;
	}

	public Short getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Short lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Byte getSalesSection() {
		return salesSection;
	}

	public void setSalesSection(Byte salesSection) {
		this.salesSection = salesSection;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getOriginalUnitPrice() {
		return originalUnitPrice;
	}

	public void setOriginalUnitPrice(Integer originalUnitPrice) {
		this.originalUnitPrice = originalUnitPrice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}
    
}