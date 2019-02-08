package w.fujiko.dto.quotations.headers;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang.StringUtils;

import w.fujiko.dto.properties.PropertyDefaultDTO;
import w.fujiko.dto.users.UserDefaultDTO;
import w.fujiko.dto.departments.DepartmentDTODefault;
import w.fujiko.dto.customers.CustomerBaseDefaultDTO;

public class QuotationHeaderDTO implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("slipNumber")
	private String slipNumber;

	@JsonProperty("version")
	private Integer version;

	@JsonProperty("workingSite")
	private PropertyDefaultDTO workingSite;

	@JsonProperty("quotationDate")
	private Date quotationDate;

	@JsonProperty("deliveryDate")
	private Date deliveryDate;

	@JsonProperty("constructionNumber")
	private String constructionNumber;

	@JsonProperty("constructionName")
	private String constructionName;

	@JsonProperty("constructionCategory")
	private Integer constructionCategory;

	@JsonProperty("constructionCategoryName")
	private String constructionCategoryName;

	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("redSlipCopyFlag")
	private boolean redSlipCopyFlag=false;

	@JsonProperty("copyOriginNumber")
	private Integer copyOriginNumber;

	@JsonProperty("constructionContractFlag")
	private boolean constructionContractFlag=false;

	@JsonProperty("warrantyCardFlag")
	private boolean warrantyCardFlag=false;

	@JsonProperty("user")
	private UserDefaultDTO user;

	@JsonProperty("department")
	private DepartmentDTODefault department;

	@JsonProperty("customerBase")
	private CustomerBaseDefaultDTO customerBase;

	@JsonProperty("customerName")
	private String customerName;

	@JsonProperty("deliveryDateMessage")
	private String deliveryDateMessage;
	
	@JsonProperty("respectUnnecessaryFlag")
	private boolean respectUnnecessaryFlag=false;
	
	@JsonProperty("contactUserName")
	private String contactUserName;
	
	@JsonProperty("postMessage")
	private String postMessage;
	
	@JsonProperty("paymentTermsMessage")
	private String paymentTermsMessage;
	
	@JsonProperty("estimatedDeadlineMessage")
	private String estimatedDeadlineMessage;
	
	@JsonProperty("deliveryPlaceMessage")
	private String deliveryPlaceMessage;
	
	@JsonProperty("sparePartsCostMessage")
	private String sparePartsCostMessage;

	@JsonProperty("standardUnitPricePrintingFlag")
	private boolean standardUnitPricePrintingFlag=false;
	
	@JsonProperty("quotationRank")
	private Integer quotationRank;
	
	@JsonProperty("poStatus")
	private Byte poStatus;
	
	@JsonProperty("purchaseStatus")
	private Byte purchaseStatus;

	@JsonProperty("salesStatus")
	private Byte salesStatus;

	@JsonProperty("billingStatus")
	private Byte billingStatus;

	@JsonProperty("paymentStatus")
	private Byte paymentStatus;
	
	@JsonProperty("quotationTotalAmount")
	private Double quotationTotalAmount;
	
	@JsonProperty("orderTotalAmount")
	private Double orderTotalAmount;
	
	@JsonProperty("purchaseTotalAmount")
	private Double purchaseTotalAmount;
	
	@JsonProperty("grossProfit")
	private Double grossProfit;

	@JsonProperty("taxRate")
	private Double taxRate;
	
	@JsonProperty("isApprove")
	private Boolean isApprove;
	
	@JsonProperty("grossMarginRatio")
	private Double grossMarginRatio;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public PropertyDefaultDTO getWorkingSite() {
		return workingSite;
	}

	public void setWorkingSite(PropertyDefaultDTO workingSite) {
		this.workingSite = workingSite;
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
		return StringUtils.isBlank(unit) ? "式" : unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean isRedSlipCopyFlag() {
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

	public boolean isConstructionContractFlag() {
		return constructionContractFlag;
	}

	public void setConstructionContractFlag(boolean constructionContractFlag) {
		this.constructionContractFlag = constructionContractFlag;
	}

	public boolean isWarrantyCardFlag() {
		return warrantyCardFlag;
	}

	public void setWarrantyCardFlag(boolean warrantyCardFlag) {
		this.warrantyCardFlag = warrantyCardFlag;
	}

	public UserDefaultDTO getUser() {
		return user;
	}

	public void setUser(UserDefaultDTO user) {
		this.user = user;
	}

	public DepartmentDTODefault getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTODefault department) {
		this.department = department;
	}

	public CustomerBaseDefaultDTO getCustomerBase() {
		return customerBase;
	}

	public void setCustomerBase(CustomerBaseDefaultDTO customerBase) {
		this.customerBase = customerBase;
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

	public boolean isRespectUnnecessaryFlag() {
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

	public boolean isStandardUnitPricePrintingFlag() {
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

	public byte getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(byte billingStatus) {
		this.billingStatus = billingStatus;
	}

	public byte getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(byte paymentStatus) {
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