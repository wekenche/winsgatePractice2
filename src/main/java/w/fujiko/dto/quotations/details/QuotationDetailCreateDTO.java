package w.fujiko.dto.quotations.details;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.NumberFormat;

public class QuotationDetailCreateDTO implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
 
    @JsonProperty("taskId")
    private short taskId;

    @JsonProperty("lineNumber")
    @NotNull
    @NumberFormat
    private int lineNumber;

    @JsonProperty("processingClassification")
    private Integer processingClassification;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("specificationType")
    private String specificationType;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("makerId")
    private Integer makerId;

    @JsonProperty("productId")
    private Integer productId;
    
    @JsonProperty("accessories")
    private String accessories;

    @JsonProperty("fixedPrice")
    private Double fixedPrice;

    @JsonProperty("supplierId")
    private Integer supplierId;

    @JsonProperty("quantity")
    @NumberFormat
    private Integer quantity;

	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("orderedQuantity")
	private Integer orderedQuantity = 0;
	
	@JsonProperty("purchasedQuantity")
	private Integer purchasedQuantity = 0;
	
	@JsonProperty("soldQuantity")
	private Integer soldQuantity = 0;

    @JsonProperty("customerRatio")
    private Double customerRatio;

    @JsonProperty("unitPrice")
    private Double unitPrice;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("suppliersRatio")
    private Double suppliersRatio;

    @JsonProperty("originalUnitPrice")
    private Double originalUnitPrice;

    @JsonProperty("costPriceAmount")
    private Double costPriceAmount;

    @JsonProperty("stockUseClassification")
    private Byte stockUseClassification;

	@JsonProperty("remarks")
	private String remarks;
	
    @JsonIgnore
    private Date dateCreated=new Date();

	@JsonProperty("createdBy")
	@NotNull
    private Integer createdById;

	@JsonProperty("createdAt")
	@NotEmpty
    private String createdAtId;

	public short getTaskId() {
		return taskId;
	}

	public void setTaskId(short taskId) {
		this.taskId = taskId;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getProcessingClassification() {
		return processingClassification;
	}

	public void setProcessingClassification(Integer processingClassification) {
		this.processingClassification = processingClassification;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSpecificationType() {
		return specificationType;
	}

	public void setSpecificationType(String specificationType) {
		this.specificationType = specificationType;
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

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public Double getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(Double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	public Integer getPurchasedQuantity() {
		return purchasedQuantity;
	}

	public void setPurchasedQuantity(Integer purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}
	
	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Double getCustomerRatio() {
		return customerRatio;
	}

	public void setCustomerRatio(Double customerRatio) {
		this.customerRatio = customerRatio;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getSuppliersRatio() {
		return suppliersRatio;
	}

	public void setSuppliersRatio(Double suppliersRatio) {
		this.suppliersRatio = suppliersRatio;
	}

	public Double getOriginalUnitPrice() {
		return originalUnitPrice;
	}

	public void setOriginalUnitPrice(Double originalUnitPrice) {
		this.originalUnitPrice = originalUnitPrice;
	}

	public Double getCostPriceAmount() {
		return costPriceAmount;
	}

	public void setCostPriceAmount(Double costPriceAmount) {
		this.costPriceAmount = costPriceAmount;
	}

	public Byte getStockUseClassification() {
		return stockUseClassification;
	}

	public void setStockUseClassification(Byte stockUseClassification) {
		this.stockUseClassification = stockUseClassification;
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