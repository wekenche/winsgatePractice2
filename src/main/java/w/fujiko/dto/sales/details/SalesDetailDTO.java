package w.fujiko.dto.sales.details;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


import w.fujiko.dto.makers.MakerDefaultDTO;
import w.fujiko.dto.products.ProductDefaultDTO;
import w.fujiko.dto.units.UnitDTO;

public class SalesDetailDTO implements Serializable {

	private static final long serialVersionUID = -2659621502091783793L;
	
    @JsonProperty("salesHeaderId")
	private Integer salesHeaderId;
	
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
	
    @JsonProperty("maker")
    private MakerDefaultDTO maker;
	
    @JsonProperty("product")
    private ProductDefaultDTO product;
	
    @JsonProperty("unit")
    private UnitDTO unit;
	
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
	
	@JsonProperty("dateCreated")
	private Date dateCreated;
	
	@JsonProperty("dateUpdated")
	private Date dateUpdated;
	
	@JsonProperty("createdBy")
	private Integer createdById;

	@JsonProperty("updatedBy")
	private Integer updatedById;

	@JsonProperty("createdAt")
	private String createdAtId;
	
	@JsonProperty("updatedAt")
	private String updatedAtId;

	public Integer getSalesHeaderId() {
		return salesHeaderId;
	}

	public void setSalesHeaderId(Integer salesHeaderId) {
		this.salesHeaderId = salesHeaderId;
	}

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

	public MakerDefaultDTO getMaker() {
		return maker;
	}

	public void setMaker(MakerDefaultDTO maker) {
		this.maker = maker;
	}

	public ProductDefaultDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDefaultDTO product) {
		this.product = product;
	}

	public UnitDTO getUnit() {
		return unit;
	}

	public void setUnit(UnitDTO unit) {
		this.unit = unit;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}
	
}