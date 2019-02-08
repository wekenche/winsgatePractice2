package w.fujiko.model.transactions.quotations.jasperdata;

public class Quotation {

	private String name;
	
	private String productName;
	
	private Integer quantity;
	
	private String unit;
	
	private Double unitPrice;
	
	private Double amount;
	
	private Double standardUnitPrice;
	
	private String remarks;

	public Quotation(String name, String productName, Integer quantity, String unit, Double unitPrice, Double amount,
			Double standardUnitPrice, String remarks) {
		super();
		this.name = name;
		this.productName = productName;
		this.quantity = quantity;
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.standardUnitPrice = standardUnitPrice;
		this.remarks = remarks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Double getStandardUnitPrice() {
		return standardUnitPrice;
	}

	public void setStandardUnitPrice(Double standardUnitPrice) {
		this.standardUnitPrice = standardUnitPrice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}