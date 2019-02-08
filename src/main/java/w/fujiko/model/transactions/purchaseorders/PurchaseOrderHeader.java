package w.fujiko.model.transactions.purchaseorders;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.destinations.Destination;
import w.fujiko.model.masters.suppliers.SupplierBase;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.quotations.QuotationHeader;

@Entity
@Table(name="trn_po_header")
public class PurchaseOrderHeader implements Serializable {

	private static final long serialVersionUID = -2997321890317550989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="purchase_order_slip_id")
	private Integer id;
	
	@NotNull
	@Column(name="purchase_order_slip_no", columnDefinition="char(6)", nullable=false)
	private String slipNumber;
	
	@NotNull
	@Column(name="purchase_order_ver_no", columnDefinition="int", nullable=false)
	private Integer version;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="quotation_slip_id", insertable=true, updatable=false, nullable=false)
	private QuotationHeader quotationHeader;
	
	@Column(name="order_status",columnDefinition="tinyint")
	private Byte orderStatus;

	@Column(name="sales_status",columnDefinition="tinyint")
	private Byte salesStatus;
	
	@Column(name="purchase_order_date", columnDefinition = "datetime")	
	private Date purchaseOrderDate;
	
	@Column(name="delivery_date", columnDefinition = "datetime")	
	private Date deliveryDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="supplier_base_id", insertable=true, updatable=false, nullable=false)
	private SupplierBase supplierBase;
	
	@Column(name="slip_type", columnDefinition="tinyint")
	private Byte slipType;
	
	@Column(name="consumption_tax_type", columnDefinition="tinyint")
	private Byte consumptionTaxType;
	
	@Column(name="sales_tax_rate", columnDefinition = "decimal")
	private Double salesTaxRate;
	
	@Column(name="contact_person_incharge", columnDefinition="nvarchar(60)")
	private String contactPersonIncharge;
	
	@Column(name="secure_flg", columnDefinition="bit default 0")
	private Boolean secureFlg;
	
	@Column(name="correction_deadline", columnDefinition = "datetime")	
	private Date correctionDeadline;
	
	@ManyToOne
	@JoinColumn(name="destination_id", insertable=true, updatable=false)
	private Destination destination;

	@ManyToOne
	@JoinColumn(name="po_user_id", insertable=true, updatable=false, nullable=false)
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name="po_department_id",columnDefinition="int",insertable=true,updatable=true,nullable=false)
	private Department department;
	
	@Column(name="purchase_type", columnDefinition="tinyint")
	private Byte purchaseType;
	
	@Column(name="remarks", columnDefinition="nvarchar(max)")
	private String remarks;
	
	@Column(name="delivery_type", columnDefinition="tinyint")
	private Byte deliveryType;
	
	@Column(name="direct_delivery", columnDefinition="tinyint")
	private Byte directDelivery;
	
	@Column(name="direct_delivery_timezone", columnDefinition="nvarchar(35)", nullable=true)
	private String directDeliveryTimezone;
	
	@Column(name="direct_delivery_designation_type", columnDefinition="tinyint")
	private Byte directDeliveryDesignationType;
	
	@Column(name="hertz_type", columnDefinition="tinyint")
	private Byte hertzType;
	
	@Column(name="price_list", columnDefinition = "decimal")
	private Double priceList;
	
	@Column(name="price_ratio", columnDefinition = "decimal")
	private Double priceRatio;
	
	@Column(name="total_purchase", columnDefinition = "decimal")
	private Double totalPurchase;
	
	@Column(name="total_return", columnDefinition = "decimal")
	private Double totalReturn;
	
	@Column(name="total_discount", columnDefinition = "decimal")
	private Double totalDiscount;
	
	@Column(name="total_order", columnDefinition = "decimal")
	private Double totalOrder;
	
	@Column(name="total_tax_included", columnDefinition = "decimal")
	private Double totalTaxIncluded;
	
	@Column(name="total_tax_consumption", columnDefinition = "decimal")
	private Double totalTaxConsumption;
	
	@Column(name="purchase_order_amount", columnDefinition = "decimal")
	private Double purchaseOrderAmount;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User createdBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program createdAt;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public QuotationHeader getQuotationHeader() {
		return quotationHeader;
	}

	public void setQuotationHeader(QuotationHeader quotationHeader) {
		this.quotationHeader = quotationHeader;
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

	public SupplierBase getSupplierBase() {
		return supplierBase;
	}

	public void setSupplierBase(SupplierBase supplierBase) {
		this.supplierBase = supplierBase;
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

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}

}