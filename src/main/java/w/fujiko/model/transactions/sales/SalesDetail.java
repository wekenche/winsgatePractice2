package w.fujiko.model.transactions.sales;

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
import w.fujiko.model.transactions.quotations.QuotationHeader;

@Entity
@Table(name="trn_sales_detail")
public class SalesDetail implements Serializable {

	private static final long serialVersionUID = -2659621502091783793L;
	
	@EmbeddedId	
	private SalesDetailPk id;
	
	@ManyToOne
	@JoinColumn(name="sales_slip_id", insertable=false, updatable=false)
	private SalesHeader salesHeader;
	
    @ManyToOne
    @NotNull
	@JoinColumn(name="quotation_slip_id", insertable=true, updatable=true, nullable=false)
	private QuotationHeader quotationHeader;
    
    @NotNull
    @NotNull
	@Column(name="task_id", columnDefinition="smallint", insertable=false, updatable=false, nullable=false)
	private Short taskId;

	@NotNull
	@Column(name="line_number", columnDefinition="smallint", nullable=false)
	private Short lineNumber;
    
    @NotNull
	@Column(name="sales_section", columnDefinition="tinyint", nullable=false)
	private Byte salesSection;
    
	@Column(name="model_number", columnDefinition="nvarchar(80)")
	private String modelNumber;
	
	@Column(name="product_name", columnDefinition="nvarchar(40)")
	private String productName;
	
	@ManyToOne
	@JoinColumn(name="maker_id", insertable=true, updatable=false, nullable=false)
	private Maker maker;
	
    @ManyToOne
    @NotNull
	@JoinColumn(name="product_id", insertable=true, updatable=false, nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="unit_id", insertable=true, updatable=false)
	private Unit unit;
	
	@Column(name="quantity", columnDefinition="int", updatable=false)
    private Integer quantity;
    
    @Column(name="unit_price", columnDefinition="decimal(18,2)")
	private Double unitPrice;
	
	@Column(name="original_unit_price", columnDefinition="decimal(18,2)", updatable=false)
	private Integer originalUnitPrice;
	
	@Column(name="amount", columnDefinition="decimal(18,2)")
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

	public SalesDetailPk getId() {
		return id;
	}

	public void setId(SalesDetailPk id) {
		this.id = id;
	}

	public SalesHeader getSalesHeader() {
		return salesHeader;
	}

	public void setSalesHeader(SalesHeader salesHeader) {
		this.salesHeader = salesHeader;
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