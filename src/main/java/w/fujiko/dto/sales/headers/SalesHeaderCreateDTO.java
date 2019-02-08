package w.fujiko.dto.sales.headers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesHeaderCreateDTO  implements Serializable {

	private static final long serialVersionUID = -2997321890317550989L;
	
	@JsonProperty("slipNumber")
	private String slipNumber;
	
	@JsonProperty("version")
	private Integer version;

	@JsonProperty("quotationHeaderId")
	@NotNull
	private Integer quotationHeaderId;
	
	@JsonProperty("salesDate")
	@NotNull
	private Date salesDate;
	
	@JsonProperty("billingDate")
	@NotNull
    private Date billingDate;

	@JsonProperty("customerBaseId")
	@NotNull
	private Integer customerBaseId;

	@JsonProperty("customerName")
	private String customerName;
    
	@JsonProperty("destinationId")
	private Integer destinationId;

	@JsonProperty("constructionNumber")
	private String constructionNumber;

	@JsonProperty("constructionName")
	private String constructionName;
    
	@JsonProperty("salesType")
	@NotNull
	private Byte salesType;
    
	@JsonProperty("constructionContractorFlag")
	private Boolean constructionContractorFlag=false;

	@JsonProperty("slipType")
	@NotNull
	private Byte slipType;
    
	@JsonProperty("consumptionTaxType")
	@NotNull
	private Byte consumptionTaxType;

	@JsonProperty("userId")
	@NotNull
	private Integer userId;

	@JsonProperty("departmentId")
	@NotNull
	private Integer departmentId;

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

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
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