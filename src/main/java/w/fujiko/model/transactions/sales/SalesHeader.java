package w.fujiko.model.transactions.sales;

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

import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.destinations.Destination;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.quotations.QuotationHeader;

@Entity
@Table(name="trn_sales_header")
public class SalesHeader implements Serializable {

	private static final long serialVersionUID = -2997321890317550989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sales_slip_id")
	private Integer id;
	
	@NotNull
	@Column(name="sales_slip_no", columnDefinition="char(6)", nullable=false)
	private String slipNumber;
	
	@NotNull
	@Column(name="sales_ver_no", columnDefinition="int", nullable=false)
	private Integer version;
	
	@ManyToOne
    @JoinColumn(name="quotation_slip_id", insertable=true, updatable=false, nullable=false)
    @NotNull
	private QuotationHeader quotationHeader;
    
    @Column(name="sales_date", columnDefinition = "datetime", nullable=false)	
    @NotNull
	private Date salesDate;
	
    @Column(name="billing_date", columnDefinition = "datetime", nullable=false)	
    @NotNull
    private Date billingDate;

    @NotNull
    @ManyToOne
	@JoinColumn(name="customer_base_id",columnDefinition="int",insertable=true,updatable=true,nullable=false)
    private CustomerBase customerBase;

	@Column(name="customer_name",columnDefinition="nvarchar(80)")
	private String customerName;
    
    @ManyToOne
	@JoinColumn(name="destination_id", insertable=true, updatable=true)
    private Destination destination;

    @Column(name="construction_no",columnDefinition="char(9)")
	private String constructionNumber;

	@Column(name="construction_name",columnDefinition="nvarchar(80)")
	private String constructionName;
    
    @NotNull
    @Column(name="sales_type", columnDefinition="tinyint",nullable=false)
    private Byte salesType;
    
    @Column(name="construction_contractor_flg", columnDefinition="bit", nullable=false)
    @NotNull
    private Boolean constructionContractorFlag;

    @NotNull
    @Column(name="slip_type", columnDefinition="tinyint", nullable=false)
    private Byte slipType;
    
    @NotNull
    @Column(name="consumption_tax_type", columnDefinition="tinyint", nullable=false)
	private Byte consumptionTaxType;

    @ManyToOne
    @NotNull
	@JoinColumn(name="sales_user_id", insertable=true, updatable=false, nullable=false)
	private User user;

	@ManyToOne
    @NotNull
	@JoinColumn(name="sales_department_id", insertable=true, updatable=false, nullable=false)
	private Department department;

    @Column(name="remarks", columnDefinition="nvarchar(max)")
    private String remarks;
    
    @Column(name="sales_tax_rate", columnDefinition = "decimal(18,2)")
    private Double salesTaxRate;
    
    @Column(name="price_list", columnDefinition = "decimal(18,2)")
    private Double priceList;
    
    @Column(name="price_ratio", columnDefinition = "decimal(18,2)")
    private Double priceRatio;

    @Column(name="sales", columnDefinition = "decimal(18,2)")
    private Double sales;
    
    @Column(name="total_return", columnDefinition = "decimal(18,2)")
	private Double totalReturn;

    @Column(name="total_discount", columnDefinition = "decimal(18,2)")
	private Double totalDiscount;
    
    @Column(name="total_sales", columnDefinition = "decimal(18,2)")
	private Double totalSales;

    @Column(name="total_tax_included", columnDefinition = "decimal(18,2)")
	private Double totalTaxIncluded;

    @Column(name="total_tax_consumption", columnDefinition = "decimal(18,2)")
    private Double totalTaxConsumption;

    @Column(name="total_gross_profit", columnDefinition = "decimal(18,2)")
    private Double totalGrossProfit;
	
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

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
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

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
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

	public Byte getSalesType() {
		return salesType;
	}

	public void setSalesType(Byte salesType) {
		this.salesType = salesType;
	}

	public Boolean getConstructionContractorFlag() {
		return constructionContractorFlag;
	}

	public void setConstructionContractorFlag(Boolean constructionContractorFlag) {
		this.constructionContractorFlag = constructionContractorFlag;
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
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getSalesTaxRate() {
		return salesTaxRate;
	}

	public void setSalesTaxRate(Double salesTaxRate) {
		this.salesTaxRate = salesTaxRate;
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

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
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

	public Double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
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

	public Double getTotalGrossProfit() {
		return totalGrossProfit;
	}

	public void setTotalGrossProfit(Double totalGrossProfit) {
		this.totalGrossProfit = totalGrossProfit;
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