package w.fujiko.model.transactions.purchaseorders.histories;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.Unit;
import w.fujiko.model.masters.products.Product;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetailPk;
import w.fujiko.model.transactions.quotations.QuotationHeader;

@Entity
@Table(name="trn_po_detail_history")
public class PurchaseOrderDetailHistory implements Serializable {

	private static final long serialVersionUID = 4953065887672174938L;
	
	@EmbeddedId
	private PurchaseOrderDetailPk id;
	
	@ManyToOne
	@JoinColumn(name="purchase_order_slip_id", insertable=false, updatable=false)
	private PurchaseOrderHeaderHistory purchaseOrderHeader;
	
	@ManyToOne
	@JoinColumn(name="quotation_slip_id", insertable=false, updatable=false)
	private QuotationHeader quotationHeader;
	
	@Column(name="task_id", columnDefinition="smallint", insertable=false, updatable=false, nullable=true)
	private Short taskId;

	@NotNull
	@Column(name="line_number", columnDefinition="smallint", nullable=false)
	private Short lineNumber;
	
	@Column(name="purchasing_section", columnDefinition="tinyint")
	private Byte purchasingSection;
	
	@Column(name="model_number", columnDefinition="nvarchar(80)")
	private String modelNumber;
	
	@Column(name="product_name", columnDefinition="nvarchar(40)")
	private String productName;
	
	@ManyToOne
	@JoinColumn(name="maker_id", insertable=true, updatable=false, nullable=false)
	private Maker maker;
	
	@ManyToOne
	@JoinColumn(name="product_id", insertable=true, updatable=false, nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="unit_id", insertable=true, updatable=false, nullable=false)
	private Unit unit;
	
	@Column(name="quantity", columnDefinition="int", updatable=false)
	private Integer quantity;
	
	@Column(name="estimated_quantity", columnDefinition="int", updatable=false)
	private Integer estimatedQuantity;
	
	@Column(name="unit_price", columnDefinition="decimal")
	private Double unitPrice;
	
	@Column(name="purchased_quantity", columnDefinition="int", updatable=false)
	private Integer purchasedQuantity;
	
	@Column(name="amount", columnDefinition="decimal")
	private Double amount;
	
	@Column(name="remarks", columnDefinition="nvarchar(max)")
	private String remarks;
	
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

	public PurchaseOrderDetailPk getId() {
		return id;
	}

	public void setId(PurchaseOrderDetailPk id) {
		this.id = id;
	}

	public PurchaseOrderHeaderHistory getPurchaseOrderHeader() {
		return purchaseOrderHeader;
	}

	public void setPurchaseOrderHeader(PurchaseOrderHeaderHistory purchaseOrderHeader) {
		this.purchaseOrderHeader = purchaseOrderHeader;
	}

	public QuotationHeader getQuotationHeader() {
		return quotationHeader;
	}

	public void setQuotationHeader(QuotationHeader quotationHeader) {
		this.quotationHeader = quotationHeader;
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

	public Byte getPurchasingSection() {
		return purchasingSection;
	}

	public void setPurchasingSection(Byte purchasingSection) {
		this.purchasingSection = purchasingSection;
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

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getEstimatedQuantity() {
		return estimatedQuantity;
	}

	public void setEstimatedQuantity(Integer estimatedQuantity) {
		this.estimatedQuantity = estimatedQuantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getPurchasedQuantity() {
		return purchasedQuantity;
	}

	public void setPurchasedQuantity(Integer purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
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