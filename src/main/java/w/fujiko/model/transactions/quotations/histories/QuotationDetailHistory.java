package w.fujiko.model.transactions.quotations.histories;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.products.Product;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.quotations.QuotationDetailPk;

@Entity
@Table(name="trn_quot_detail_history")
public class QuotationDetailHistory implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
	
	@EmbeddedId
	private QuotationDetailPk id; 

	@ManyToOne
    @JoinColumn(name="quotation_slip_id",insertable=false,updatable=false)
	private QuotationHeaderHistory quotationHeaderHistory;

	@Column(name="task_id",columnDefinition="short",insertable=false,updatable=false)
	private short taskId;

	@NotNull
	@Column(name="line_number",columnDefinition="int",nullable=false)
	private int lineNumber;

	@Column(name="processing_classification",columnDefinition="int",nullable=true)
	private Integer processingClassification;

	@Column(name="symbol",columnDefinition="nvarchar(30)",nullable=true)
	private String symbol;

	@Column(name="specification_type",columnDefinition="nvarchar(80)",nullable=true)
	private String specificationType;

	@Column(name="product_name",columnDefinition="nvarchar(40)",nullable=true)
	private String productName;

    @ManyToOne
    @JoinColumn(name="maker_id",updatable=false)
    @NotNull
    private Maker maker;

    @ManyToOne
    @JoinColumn(name="product_id",updatable=false)
    @NotNull
    private Product product;
    
	@Column(name="accessories",columnDefinition="nvarchar(4)")
	private String accessories;

	@Column(name="fixed_price",columnDefinition="decimal(18,2)")
	private Double fixedPrice;

    @Column(name="suppliers_id",columnDefinition="int",updatable=false)
    private Integer supplierId;

    @Column(name="quantity",columnDefinition="int",updatable=false)
	private Integer quantity;
	
	@Column(name="unit",columnDefinition="nvarchar(4)")
	private String unit;
	
	@Column(name="ordered_quantity",columnDefinition="int Default 0")
	private Integer orderedQuantity;
	
	@Column(name="purchased_quantity",columnDefinition="int Default 0")
	private Integer purchasedQuantity;
	
	@Column(name="sold_quantity",columnDefinition="int Default 0")
    private Integer soldQuantity;

    @Column(name="customer_ratio",columnDefinition="decimal(18,2)")
    private Double customerRatio;

    @Column(name="unit_price",columnDefinition="decimal(18,2)")
    private Double unitPrice;

    @Column(name="amount",columnDefinition="decimal(18,2)")
    private Double amount;

    @Column(name="suppliers_ratio",columnDefinition="decimal(18,2)")
    private Double suppliersRatio;

    @Column(name="orig_unit_price",columnDefinition="decimal(18,2)")
    private Double originalUnitPrice;
    
    @Column(name="cost_price_amount",columnDefinition="decimal(18,2)")
    private Double costPriceAmount;

    @Column(name="stock_use_classification",columnDefinition="tinyint")
    private Byte stockUseClassification;

	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@UpdateTimestamp
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
	
	public QuotationDetailPk getId() {
		return id;
	}

	public void setId(QuotationDetailPk id) {
		this.id = id;
	}

	public QuotationHeaderHistory setQuotationHeaderHistory() {
		return quotationHeaderHistory;
	}

	public void setQuotationHeaderHistory(QuotationHeaderHistory quotationHeaderHistory) {
		this.quotationHeaderHistory = quotationHeaderHistory;
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