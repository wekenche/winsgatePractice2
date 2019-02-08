package w.fujiko.model.masters.products;

public class ProductExtractionModel {
	
	private Integer productCode;
	private String productName;
	private Integer productItemCode;
	private String productItemName;
	private String dateCreated;
	private String lastUpdated;
	
	public ProductExtractionModel(Integer productCode, String productName, Integer productItemCode,
			String productItemName, String dateCreated, String lastUpdated) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productItemCode = productItemCode;
		this.productItemName = productItemName;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductItemCode() {
		return productItemCode;
	}

	public void setProductItemCode(Integer productItemCode) {
		this.productItemCode = productItemCode;
	}

	public String getProductItemName() {
		return productItemName;
	}

	public void setProductItemName(String productItemName) {
		this.productItemName = productItemName;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}