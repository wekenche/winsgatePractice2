package w.fujiko.dto.purchaseorders.headers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrderHeaderCreateDTO implements Serializable {

	private static final long serialVersionUID = -4135931683626583011L;
	
	@JsonProperty("slipNumber")
	private String slipNumber;
	
	@JsonProperty("version")
	private Integer version;
	
	@JsonProperty("quotationHeaderId")
	private Integer quotationHeaderId;
	
	@JsonProperty("orderStatus")
	private Byte orderStatus;
	
	@JsonProperty("salesStatus")
	private Byte salesStatus;
	
	@JsonProperty("purchaseOrderDate")
	private Date purchaseOrderDate;
	
	@JsonProperty("deliveryDate")
	private Date deliveryDate;
	
	@JsonProperty("supplierBaseId")
	private Integer supplierBaseId;
	
	@JsonProperty("slipType")
	private Byte slipType;
	
	@JsonProperty("consumptionTaxType")
	private Byte consumptionTaxType;
	
	@JsonProperty("salesTaxRate")
	private Double salesTaxRate;
	
	@JsonProperty("contactPersonIncharge")
	private String contactPersonIncharge;
	
	@JsonProperty("secureFlg")
	private Boolean secureFlg;
	
	@JsonProperty("correctionDeadline")
	private Date correctionDeadline;
	
	@JsonProperty("destinationId")
	private Integer destinationId;

	@JsonProperty("userId")
	private Integer userId;

	@JsonProperty("departmentId")
	private Integer departmentId;
	
	@JsonProperty("purchaseType")
	private Byte purchaseType;
	
	@JsonProperty("remarks")
	private String remarks;
	
	@JsonProperty("deliveryType")
	private Byte deliveryType;
	
	@JsonProperty("directDelivery")
	private Byte directDelivery;
	
	@JsonProperty("directDeliveryTimezone")
	private String directDeliveryTimezone;
	
	@JsonProperty("directDeliveryDesignationType")
	private Byte directDeliveryDesignationType;
	
	@JsonProperty("hertzType")
	private Byte hertzType;
	
	@JsonProperty("priceList")
	private Double priceList;
	
	@JsonProperty("priceRatio")
	private Double priceRatio;
	
	@JsonProperty("totalPurchase")
	private Double totalPurchase;
	
	@JsonProperty("totalReturn")
	private Double totalReturn;
	
	@JsonProperty("totalDiscount")
	private Double totalDiscount;
	
	@JsonProperty("totalOrder")
	private Double totalOrder;
	
	@JsonProperty("totalTaxIncluded")
	private Double totalTaxIncluded;
	
	@JsonProperty("totalTaxConsumption")
	private Double totalTaxConsumption;
	
	@JsonProperty("purchaseOrderAmount")
	private Double purchaseOrderAmount;
	
	@JsonIgnore
	private Date dateCreated = new Date();
	
	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;

	public String getSlipNumber() {
		return slipNumber;
	}

	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getQuotationHeaderId() {
		return quotationHeaderId;
	}

	public void setQuotationHeaderId(Integer quotationHeaderId) {
		this.quotationHeaderId = quotationHeaderId;
	}

	public Byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Byte getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(Byte salesStatus) {
		this.salesStatus = salesStatus;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getSupplierBaseId() {
		return supplierBaseId;
	}

	public void setSupplierBaseId(Integer supplierBaseId) {
		this.supplierBaseId = supplierBaseId;
	}

	public Byte getSlipType() {
		return slipType;
	}

	public void setSlipType(Byte slipType) {
		this.slipType = slipType;
	}

	public Byte getConsumptionTaxType() {
		return consumptionTaxType;
	}

	public void setConsumptionTaxType(Byte consumptionTaxType) {
		this.consumptionTaxType = consumptionTaxType;
	}

	public Double getSalesTaxRate() {
		return salesTaxRate;
	}

	public void setSalesTaxRate(Double salesTaxRate) {
		this.salesTaxRate = salesTaxRate;
	}

	public String getContactPersonIncharge() {
		return contactPersonIncharge;
	}

	public void setContactPersonIncharge(String contactPersonIncharge) {
		this.contactPersonIncharge = contactPersonIncharge;
	}

	public Boolean getSecureFlg() {
		return secureFlg;
	}

	public void setSecureFlg(Boolean secureFlg) {
		this.secureFlg = secureFlg;
	}

	public Date getCorrectionDeadline() {
		return correctionDeadline;
	}

	public void setCorrectionDeadline(Date correctionDeadline) {
		this.correctionDeadline = correctionDeadline;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setdepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Byte getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Byte purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Byte getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Byte deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Byte getDirectDelivery() {
		return directDelivery;
	}

	public void setDirectDelivery(Byte directDelivery) {
		this.directDelivery = directDelivery;
	}

	public String getDirectDeliveryTimezone() {
		return directDeliveryTimezone;
	}

	public void setDirectDeliveryTimezone(String directDeliveryTimezone) {
		this.directDeliveryTimezone = directDeliveryTimezone;
	}

	public Byte getDirectDeliveryDesignationType() {
		return directDeliveryDesignationType;
	}

	public void setDirectDeliveryDesignationType(Byte directDeliveryDesignationType) {
		this.directDeliveryDesignationType = directDeliveryDesignationType;
	}

	public Byte getHertzType() {
		return hertzType;
	}

	public void setHertzType(Byte hertzType) {
		this.hertzType = hertzType;
	}

	public Double getPriceList() {
		return priceList;
	}

	public void setPriceList(Double priceList) {
		this.priceList = priceList;
	}

	public Double getPriceRatio() {
		return priceRatio;
	}

	public void setPriceRatio(Double priceRatio) {
		this.priceRatio = priceRatio;
	}

	public Double getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(Double totalPurchase) {
		this.totalPurchase = totalPurchase;
	}

	public Double getTotalReturn() {
		return totalReturn;
	}

	public void setTotalReturn(Double totalReturn) {
		this.totalReturn = totalReturn;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Double getTotalTaxIncluded() {
		return totalTaxIncluded;
	}

	public void setTotalTaxIncluded(Double totalTaxIncluded) {
		this.totalTaxIncluded = totalTaxIncluded;
	}

	public Double getTotalTaxConsumption() {
		return totalTaxConsumption;
	}

	public void setTotalTaxConsumption(Double totalTaxConsumption) {
		this.totalTaxConsumption = totalTaxConsumption;
	}

	public Double getPurchaseOrderAmount() {
		return purchaseOrderAmount;
	}

	public void setPurchaseOrderAmount(Double purchaseOrderAmount) {
		this.purchaseOrderAmount = purchaseOrderAmount;
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