package w.fujiko.dto.sales.headers;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.customers.CustomerBaseDefaultDTO;
import w.fujiko.dto.departments.DepartmentDTODefault;
import w.fujiko.dto.destinations.DestinationDefaultDTO;
import w.fujiko.dto.users.UserDefaultDTO;

public class SalesHeaderDTO  implements Serializable {

	private static final long serialVersionUID = -2997321890317550989L;
    
    @JsonProperty("id")
    private Integer id;
    
	@JsonProperty("slipNumber")
	private String slipNumber;
    
    @JsonProperty("version")
    private Integer version;
    
	@JsonProperty("quotationHeaderId")
	private Integer quotationHeaderId;
	
	@JsonProperty("salesDate")
	private Date salesDate;
	
	@JsonProperty("billingDate")
    private Date billingDate;

	@JsonProperty("customerBase")
	private CustomerBaseDefaultDTO customerBase;

	@JsonProperty("customerName")
	private String customerName;
    
	@JsonProperty("destination")
	private DestinationDefaultDTO destination;

	@JsonProperty("constructionNumber")
	private String constructionNumber;

	@JsonProperty("constructionName")
	private String constructionName;
    
	@JsonProperty("salesType")
	private Byte salesType;
    
	@JsonProperty("constructionContractorFlag")
	private Boolean constructionContractorFlag;

	@JsonProperty("slipType")
	private Byte slipType;
    
	@JsonProperty("consumptionTaxType")
	private Byte consumptionTaxType;

	@JsonProperty("user")
	private UserDefaultDTO user;

	@JsonProperty("department")
	private DepartmentDTODefault department;

	@JsonProperty("remarks")
	private String remarks;
    
	@JsonProperty("salesTaxRate")
	private Double salesTaxRate;
    
	@JsonProperty("priceList")
	private Double priceList;
    
	@JsonProperty("priceRatio")
	private Double priceRatio;

	@JsonProperty("sales")
	private Double sales;
    
	@JsonProperty("totalReturn")
	private Double totalReturn;

	@JsonProperty("totalDiscount")
	private Double totalDiscount;
    
	@JsonProperty("totalSales")
	private Double totalSales;

	@JsonProperty("totalTaxIncluded")
	private Double totalTaxIncluded;

	@JsonProperty("totalTaxConsumption")
	private Double totalTaxConsumption;

	@JsonProperty("totalGrossProfit")
	private Double totalGrossProfit;
	
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

	public Integer getQuotationHeaderId() {
		return quotationHeaderId;
	}

	public void setQuotationHeaderId(Integer quotationHeaderId) {
		this.quotationHeaderId = quotationHeaderId;
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

	public DestinationDefaultDTO getDestination() {
		return destination;
	}

	public void setDestination(DestinationDefaultDTO destination) {
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