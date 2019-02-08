package w.fujiko.model.transactions.quotations.jasperdata;

import java.util.List;

public class QuotationWrapper {

	private String constructionNumber;

	private String propertyName;

	private String constructionCategoryName;

	private Double totalAmount;

	private List<Quotation> quotationItems;

	public QuotationWrapper(String constructionNumber, String propertyName, String constructionCategoryName,
			Double totalAmount, List<Quotation> quotationItems) {
		super();
		this.constructionNumber = constructionNumber;
		this.propertyName = propertyName;
		this.constructionCategoryName = constructionCategoryName;
		this.totalAmount = totalAmount;
		this.quotationItems = quotationItems;
	}

	public String getConstructionNumber() {
		return constructionNumber;
	}

	public void setConstructionNumber(String constructionNumber) {
		this.constructionNumber = constructionNumber;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getConstructionCategoryName() {
		return constructionCategoryName;
	}

	public void setConstructionCategoryName(String constructionCategoryName) {
		this.constructionCategoryName = constructionCategoryName;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Quotation> getQuotationItems() {
		return quotationItems;
	}

	public void setQuotationItems(List<Quotation> quotationItems) {
		this.quotationItems = quotationItems;
	}

}