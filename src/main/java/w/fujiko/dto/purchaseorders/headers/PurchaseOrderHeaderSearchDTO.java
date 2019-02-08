package w.fujiko.dto.purchaseorders.headers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class PurchaseOrderHeaderSearchDTO implements Serializable {
	
	private static final long serialVersionUID = 3179790605579566070L;
	
	private Integer workingSiteNo;
	
	private String workingSiteName;
	
	private String constructionNo;
	
	private String constructionName;
	
	private List<Byte> orderStatus;
	
	private List<Byte> salesStatus;
	
	private String supplierCode;
	
	private String supplierName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDateFrom;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDateTo;
	
	private Short userCode;
	
	private String username;
	
	private String departmentCode;
	
	private String departmentName;
	
	private String slipNum;
	
	private Boolean secureFlg;
	
	private Integer first = 0;
	
	private Integer max = 30;

	public Integer getWorkingSiteNo() {
		return workingSiteNo;
	}

	public void setWorkingSiteNo(Integer workingSiteNo) {
		this.workingSiteNo = workingSiteNo;
	}

	public String getWorkingSiteName() {
		return workingSiteName;
	}

	public void setWorkingSiteName(String workingSiteName) {
		this.workingSiteName = workingSiteName;
	}

	public String getConstructionNo() {
		return constructionNo;
	}

	public void setConstructionNo(String constructionNo) {
		this.constructionNo = constructionNo;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public List<Byte> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<Byte> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Byte> getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(List<Byte> salesStatus) {
		this.salesStatus = salesStatus;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getOrderDateFrom() {
		return orderDateFrom;
	}

	public void setOrderDateFrom(Date orderDateFrom) {
		this.orderDateFrom = orderDateFrom;
	}

	public Date getOrderDateTo() {
		return orderDateTo;
	}

	public void setOrderDateTo(Date orderDateTo) {
		this.orderDateTo = orderDateTo;
	}

	public Short getUserCode() {
		return userCode;
	}

	public void setUserCode(Short userCode) {
		this.userCode = userCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSlipNum() {
		return slipNum;
	}

	public void setSlipNum(String slipNum) {
		this.slipNum = slipNum;
	}

	public Boolean getSecureFlg() {
		return secureFlg;
	}

	public void setSecureFlg(Boolean secureFlg) {
		this.secureFlg = secureFlg;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

}