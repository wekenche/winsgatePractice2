package w.fujiko.dto.quotations.headers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import w.fujiko.annotations.QuotationSlipNumberConstraint;

public class QuotationHeaderCreateDTO implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
	
	@JsonProperty("slipNumber")
	@QuotationSlipNumberConstraint
	@Length(min=0,max=6)
	private String slipNumber;

	@JsonProperty("workingSiteId")
	@NotNull
	private Integer workingSiteId;

	@JsonProperty("quotationDate")
	@DateTimeFormat
	@NotNull
	private Date quotationDate;

	@JsonProperty("deliveryDate")
	@DateTimeFormat
	@NotNull
	private Date deliveryDate;

	@JsonProperty("constructionNumber")
	@NotNull
	@Length(min=9,max=9)
	private String constructionNumber;

	@JsonProperty("constructionName")
	@NotNull
	@Length(min=9,max=9)
	private String constructionName;

	@JsonProperty("constructionCategory")
	@NotNull
	private int constructionCategory;

	@JsonProperty("constructionCategoryName")
	@NotNull
	@Length(min=1,max=50)
	private String constructionCategoryName;
	
	@JsonProperty("unit")
	private String unit = "式";

	@JsonProperty("redSlipCopyFlag")
	private boolean redSlipCopyFlag=false;

	@JsonProperty("copyOriginNumber")
	private Integer copyOriginNumber;

	@JsonProperty("constructionContractFlag")
	private boolean constructionContractFlag=false;

	@JsonProperty("warrantyCardFlag")
	private boolean warrantyCardFlag=false;

	@JsonProperty("userId")
	@NotNull
	private Integer userId;

	@JsonProperty("departmentId")
	@NotNull
	private Integer departmentId;

	@JsonProperty("customerBaseId")
	@NotNull
	private Integer customerBaseId;

	@JsonProperty("customerName")
	@Length(min=0,max=80)
	private String customerName;
	
	@JsonProperty("deliveryDateMessage")
	@Length(min=0,max=250)
	private String deliveryDateMessage;
	
	@JsonProperty("respectUnnecessaryFlag")
	private boolean respectUnnecessaryFlag=false;
	
	@JsonProperty("contactUserName")
	@Length(min=0,max=50)
	private String contactUserName;
	
	@JsonProperty("postMessage")
	@Length(min=0,max=250)
	private String postMessage;
	
	@JsonProperty("paymentTermsMessage")
	@Length(min=0,max=250)
	private String paymentTermsMessage;
	
	@JsonProperty("estimatedDeadlineMessage")
	@Length(min=0,max=250)
	private String estimatedDeadlineMessage;
	
	@JsonProperty("deliveryPlaceMessage")
	@Length(min=0,max=250)
	private String deliveryPlaceMessage;
	
	@JsonProperty("sparePartsCostMessage")
	@Length(min=0,max=250)
	private String sparePartsCostMessage;

	@JsonProperty("standardUnitPricePrintingFlag")
	private boolean standardUnitPricePrintingFlag=false;
	
	@JsonProperty("quotationRank")
	private Integer quotationRank;
	
	@JsonProperty("poStatus")
	private Byte poStatus = 0;
	
	@JsonProperty("purchaseStatus")
	private Byte purchaseStatus = 0;

	@JsonProperty("salesStatus")
	private Byte salesStatus = 0;

	@JsonProperty("billingStatus")
	private Byte billingStatus = 0;

	@JsonProperty("paymentStatus")
	private Byte paymentStatus = 0;
	
	@JsonProperty("quotationTotalAmount")
	private Double quotationTotalAmount = 0.00;
	
	@JsonProperty("orderTotalAmount")
	private Double orderTotalAmount = 0.00;
	
	@JsonProperty("purchaseTotalAmount")
	private Double purchaseTotalAmount = 0.00;
	
	@JsonProperty("grossProfit")
	private Double grossProfit = 0.00;
	
	@JsonProperty("taxRate")
	@NotNull
	private Double taxRate;
	
	@JsonProperty("isApprove")
	private Boolean isApprove = true;

	@JsonProperty("grossMarginRatio")
	private Double grossMarginRatio = 0.00;

	@JsonIgnore
	private Date dateCreated = new Date();
	
	@JsonProperty("createdBy")
	@NotNull
	private Integer createdBy;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAt;

	public String getSlipNumber() {
		return slipNumber;
	}

	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}

	public Integer getWorkingSiteId() {
		return workingSiteId;
	}

	public void setWorkingSiteId(Integer workingSiteId) {
		this.workingSiteId = workingSiteId;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getConstructionNumber() {
		return constructionNumber;
	}

	public void setConstructionNumber(String constructionNumber) {
		this.constructionNumber = constructionNumber;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public Integer getConstructionCategory() {
		return constructionCategory;
	}

	public void setConstructionCategory(Integer constructionCategory) {
		this.constructionCategory = constructionCategory;
	}

	public String getConstructionCategoryName() {
		return constructionCategoryName;
	}

	public void setConstructionCategoryName(String constructionCategoryName) {
		this.constructionCategoryName = constructionCategoryName;
	}

	public String getUnit() {
		return  unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean getRedSlipCopyFlag() {
		return redSlipCopyFlag;
	}

	public void setRedSlipCopyFlag(boolean redSlipCopyFlag) {
		this.redSlipCopyFlag = redSlipCopyFlag;
	}

	public Integer getCopyOriginNumber() {
		return copyOriginNumber;
	}

	public void setCopyOriginNumber(Integer copyOriginNumber) {
		this.copyOriginNumber = copyOriginNumber;
	}

	public boolean getConstructionContractFlag() {
		return constructionContractFlag;
	}

	public void setConstructionContractFlag(boolean constructionContractFlag) {
		this.constructionContractFlag = constructionContractFlag;
	}

	public boolean getWarrantyCardFlag() {
		return warrantyCardFlag;
	}

	public void setWarrantyCardFlag(boolean warrantyCardFlag) {
		this.warrantyCardFlag = warrantyCardFlag;
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

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCustomerBaseId() {
		return customerBaseId;
	}

	public void setCustomerBaseId(Integer customerBaseId) {
		this.customerBaseId = customerBaseId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryDateMessage() {
		return deliveryDateMessage;
	}

	public void setDeliveryDateMessage(String deliveryDateMessage) {
		this.deliveryDateMessage = deliveryDateMessage;
	}

	public boolean getRespectUnnecessaryFlag() {
		return respectUnnecessaryFlag;
	}

	public void setRespectUnnecessaryFlag(boolean respectUnnecessaryFlag) {
		this.respectUnnecessaryFlag = respectUnnecessaryFlag;
	}

	public String getContactUserName() {
		return contactUserName;
	}

	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}

	public String getPaymentTermsMessage() {
		return paymentTermsMessage;
	}

	public void setPaymentTermsMessage(String paymentTermsMessage) {
		this.paymentTermsMessage = paymentTermsMessage;
	}

	public String getEstimatedDeadlineMessage() {
		return estimatedDeadlineMessage;
	}

	public void setEstimatedDeadlineMessage(String estimatedDeadlineMessage) {
		this.estimatedDeadlineMessage = estimatedDeadlineMessage;
	}

	public String getDeliveryPlaceMessage() {
		return deliveryPlaceMessage;
	}

	public void setDeliveryPlaceMessage(String deliveryPlaceMessage) {
		this.deliveryPlaceMessage = deliveryPlaceMessage;
	}

	public String getSparePartsCostMessage() {
		return sparePartsCostMessage;
	}

	public void setSparePartsCostMessage(String sparePartsCostMessage) {
		this.sparePartsCostMessage = sparePartsCostMessage;
	}

	public boolean getStandardUnitPricePrintingFlag() {
		return standardUnitPricePrintingFlag;
	}

	public void setStandardUnitPricePrintingFlag(boolean standardUnitPricePrintingFlag) {
		this.standardUnitPricePrintingFlag = standardUnitPricePrintingFlag;
	}

	public Integer getQuotationRank() {
		return quotationRank;
	}

	public void setQuotationRank(Integer quotationRank) {
		this.quotationRank = quotationRank;
	}

	public Byte getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(Byte poStatus) {
		this.poStatus = poStatus;
	}

	public Byte getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(Byte purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Byte getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(Byte salesStatus) {
		this.salesStatus = salesStatus;
	}

	public Byte getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(Byte billingStatus) {
		this.billingStatus = billingStatus;
	}

	public Byte getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Byte paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Double getQuotationTotalAmount() {
		return quotationTotalAmount;
	}

	public void setQuotationTotalAmount(Double quotationTotalAmount) {
		this.quotationTotalAmount = quotationTotalAmount;
	}

	public Double getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public Double getPurchaseTotalAmount() {
		return purchaseTotalAmount;
	}

	public void setPurchaseTotalAmount(Double purchaseTotalAmount) {
		this.purchaseTotalAmount = purchaseTotalAmount;
	}

	public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

	public Double getGrossMarginRatio() {
		return grossMarginRatio;
	}

	public void setGrossMarginRatio(Double grossMarginRatio) {
		this.grossMarginRatio = grossMarginRatio;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}