package w.fujiko.model.masters.properties;

public class PropertyCSVExtractionModel {
	
	private Integer propertyNo;
	private String propertyKana;
	private String propertyName;
	private String owner;
	private Short userCode;
	private String username;
	private Integer branchCode;
	private String branchName;
	private String dateCreated;
	private String lastUpdated;

	public PropertyCSVExtractionModel(Integer propertyNo, String propertyKana, String propertyName, String owner,
			Short userCode, String username, Integer branchCode, String branchName, String dateCreated,
			String lastUpdated) {
		super();
		this.propertyNo = propertyNo;
		this.propertyKana = propertyKana;
		this.propertyName = propertyName;
		this.owner = owner;
		this.userCode = userCode;
		this.username = username;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

	public Integer getPropertyNo() {
		return propertyNo;
	}
	public void setPropertyNo(Integer propertyNo) {
		this.propertyNo = propertyNo;
	}
	public String getPropertyKana() {
		return propertyKana;
	}
	public void setPropertyKana(String propertyKana) {
		this.propertyKana = propertyKana;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public Integer getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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