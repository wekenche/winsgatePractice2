package w.fujiko.model.transactions.purchaseorders.jasperdata;

public class PurchaseOrderDetailModel {

	private String classification;
	
	private String productCode;
	
	private String productName;
	
	private Integer orderQuantity;
	
	private String remarks;

	public PurchaseOrderDetailModel(String classification, String productCode, String productName,
			Integer orderQuantity, String remarks) {
		super();
		this.classification = classification;
		this.productCode = productCode;
		this.productName = productName;
		this.orderQuantity = orderQuantity;
		this.remarks = remarks;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}