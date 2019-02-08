package w.fujiko.model.transactions.quotations.histories;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.properties.Property;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="trn_quot_header_history")
public class QuotationHeaderHistory implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
	
	@Id
	@Column(name="quotation_slip_id")
	private Integer id;

	@NotNull
	@Length(min=6,max=6)
	@Column(name="quotation_slip_No",columnDefinition="char(6)",nullable=false)
	private String slipNumber;

	@NotNull
	@Column(name="quotation_slip_ver_no",columnDefinition="int",nullable=false)
	private int version;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@NotNull
	@JoinColumn(name="workingsite_id", insertable=true, updatable = true,nullable=false)
	private Property workingSite;

	@NotNull
	@Column(name="quotation_date",columnDefinition="datetime",nullable=false)
	private Date quotationDate;
	
	@NotNull
	@Column(name="delivery_date",columnDefinition="datetime",nullable=false)
	private Date deliveryDate;

	@NotNull
	@Length(min=9,max=9)
	@Column(name="construction_no",columnDefinition="char(9)" ,nullable=false)
	private String constructionNumber;

	@NotNull
	@Length(min=1,max=80)
	@Column(name="construction_name",columnDefinition="nvarchar(80)",nullable=false)
	private String constructionName;

	@NotNull
	@Column(name="construction_category_cd",columnDefinition="int",nullable=false)
	private Integer constructionCategory;

	@NotNull
	@Length(min=1,max=50)
	@Column(name="construction_category_name",columnDefinition="nvarchar(50)",nullable=false)
	private String constructionCategoryName;

	@Column(name="category_unit",columnDefinition="nvarchar(4)")
	private String unit;

	@NotNull
	@Column(name="red_slip_copy_flg",columnDefinition="bit default 0",nullable=false)
	private boolean redSlipCopyFlag;

	@Column(name="copy_origin_no",columnDefinition="int")
	private Integer copyOriginNumber;

	@NotNull
	@Column(name="construction_contract_flg",columnDefinition="bit default 0",nullable=false)
	private boolean constructionContractFlag;
	
	@NotNull
	@Column(name="warranty_card_flg",columnDefinition="bit default 0",nullable=false)
	private boolean warrantyCardFlag;

	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id",columnDefinition="int",insertable=true,updatable=true,nullable=false)
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name="department_id",columnDefinition="int",insertable=true,updatable=true,nullable=false)
	private Department department;

	@NotNull
	@ManyToOne
	@JoinColumn(name="customer_base_id",columnDefinition="int",insertable=true,updatable=true,nullable=false)
	private CustomerBase customerBase;

	@Length(min=0,max=80)
	@Column(name="customer_name",columnDefinition="nvarchar(80)")
	private String customerName;

	@Length(min=0,max=250)
	@Column(name="delivery_date_msg",columnDefinition="nvarchar(250)")
	private String deliveryDateMessage;

	@NotNull
	@Column(name="respect_unnecessary_flg",columnDefinition="bit default 0",nullable=false)
	private boolean respectUnnecessaryFlag;

	@Length(min=0,max=50)
	@Column(name="contact_user_name",columnDefinition="nvarchar(50)")
	private String contactUserName;

	@Length(min=0,max=250)
	@Column(name="post_msg",columnDefinition="nvarchar(250)")
	private String postMessage;

	@Length(min=0,max=250)
	@Column(name="payment_terms_msg",columnDefinition="nvarchar(250)")
	private String paymentTermsMessage;

	@Length(min=0,max=250)
	@Column(name="estimated_deadline_msg",columnDefinition="nvarchar(250)")
	private String estimatedDeadlineMessage;

	@Length(min=0,max=250)
	@Column(name="delivery_place_msg",columnDefinition="nvarchar(250)")
	private String deliveryPlaceMessage;

	@Length(min=0,max=250)
	@Column(name="spare_parts_cost_msg",columnDefinition="nvarchar(250)")
	private String sparePartsCostMessage;

	@NotNull
	@Column(name="standard_unit_price_printing_flg",columnDefinition="bit default 0",nullable=false)
	private boolean standardUnitPricePrintingFlag;

	@Column(name="quotation_rank",columnDefinition="int default 0",nullable=false)
	private Integer quotationRank;

	@Column(name="po_status",columnDefinition="tinyint default 0")
	private Byte poStatus;
	
	@Column(name="purchase_status",columnDefinition="tinyint default 0")
	private Byte purchaseStatus;

	@Column(name="sales_status",columnDefinition="tinyint default 0")
	private Byte salesStatus;
	
	@Column(name="billing_status",columnDefinition="tinyint default 0")
	private Byte billingStatus;

	@Column(name="payment_status",columnDefinition="tinyint default 0")
	private Byte paymentStatus;

	@Column(name="quotation_total_amount",columnDefinition="decimal(18,2) default 0.00")
	private Double quotationTotalAmount;
	
	@Column(name="order_total_amount",columnDefinition="decimal(18,2) default 0.00")
	private Double orderTotalAmount;
	
	@Column(name="purchase_total_amount",columnDefinition="decimal(18,2) default 0.00")
	private Double purchaseTotalAmount;
	
	@Column(name="gross_profit",columnDefinition="decimal(18,2) default 0.00")
	private Double grossProfit;

	@Column(name="gross_margin_ratio",columnDefinition="decimal(18,2) default 0.00")
	private Double grossMarginRatio;

	@Column(name="tax_rate",columnDefinition="decimal(18,2)")
	private Double taxRate;

	@Column(name="is_approve",columnDefinition="bit default 1")
	private Boolean isApprove;
		
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@JsonBackReference(value = "created_by")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User createdBy;

	@JsonBackReference(value = "updated_by")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program createdAt;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Property getWorkingSite() {
		return workingSite;
	}

	public void setWorkingSite(Property workingSite) {
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
		return unit;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public CustomerBase getCustomerBase() {
		return customerBase;
	}

	public void setCustomerBase(CustomerBase customerBase) {
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

	public int getQuotationRank() {
		return quotationRank;
	}

	public void setQuotationRank(int quotationRank) {
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}

}