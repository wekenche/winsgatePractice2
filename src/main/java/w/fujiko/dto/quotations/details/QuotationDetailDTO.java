package w.fujiko.dto.quotations.details;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

import w.fujiko.dto.makers.MakerDefaultDTO;
import w.fujiko.dto.products.ProductDefaultDTO;

public class QuotationDetailDTO implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
    
    @JsonProperty("quotationHeaderId")
	private Integer quotationHeaderId;

    @JsonProperty("taskId")
    private short taskId;

    @JsonProperty("lineNumber")
    private int lineNumber;

    @JsonProperty("processingClassification")
    private Integer processingClassification;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("specificationType")
    private String specificationType;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("maker")
    private MakerDefaultDTO maker;

    @JsonProperty("product")
    private ProductDefaultDTO product;
    
    @JsonProperty("accessories")
    private String accessories;

    @JsonProperty("fixedPrice")
    private Double fixedPrice;

    @JsonProperty("supplierId")
    private Integer supplierId;

    @JsonProperty("quantity")
    private Integer quantity;

	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("orderedQuantity")
	private Integer orderedQuantity;
	
	@JsonProperty("purchasedQuantity")
	private Integer purchasedQuantity;
	
	@JsonProperty("soldQuantity")
	private Integer soldQuantity;
	
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

    @JsonProperty("dateCreated")
    @DateTimeFormat
    private Date dateCreated;
	
    @JsonProperty("dateUpdated")
    @DateTimeFormat
    private Date dateUpdated;

    @JsonProperty("createdById")
    private Integer createdById;

    @JsonProperty("updatedById")
    private Integer updatedById;

    @JsonProperty("createdAt")
    private String createdAtId;
	
    @JsonProperty("updatedAt")
    private String updatedAtId;

	public Integer getQuotationHeaderId() {
		return quotationHeaderId;
	}

	public void setQuotationHeaderId(Integer quotationHeaderId) {
		this.quotationHeaderId = quotationHeaderId;
	}

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
		return (orderedQuantity !=null ? orderedQuantity : 0);
	}

	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	public Integer getPurchasedQuantity() {
		return (purchasedQuantity !=null ? purchasedQuantity : 0);
	}

	public void setPurchasedQuantity(Integer purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}
	
	public Integer getSoldQuantity() {
		return (soldQuantity !=null ? soldQuantity : 0);
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