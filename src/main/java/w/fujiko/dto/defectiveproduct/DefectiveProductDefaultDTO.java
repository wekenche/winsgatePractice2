package w.fujiko.dto.defectiveproduct;

import java.io.Serializable;
import java.util.Date;

import w.fujiko.model.masters.products.Product;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

public class DefectiveProductDefaultDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Date datePurchased;
	
	private Integer totalDefectiveProduct;
	
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Integer getTotalDefectiveProduct() {
		return totalDefectiveProduct;
	}

	public void setTotalDefectiveProduct(Integer totalDefectiveProduct) {
		this.totalDefectiveProduct = totalDefectiveProduct;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/*private Date dateCreated;
	
	private Date dateUpdated;
	
	private Program createdAt;
	
	private Program updatedAt;
	
	private User createdBy;
	
	private User updatedBy;*/
	
}
