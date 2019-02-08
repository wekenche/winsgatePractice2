package w.fujiko.model.masters.properties;

public class PropertyPDFExtractionModel {
	
	private Integer propertyNo;
	private String propertyNameAndKana;
	private String userAndBranch;
	private String owner;
	private String dateCreated;
	private String lastUpdated;
	
	public PropertyPDFExtractionModel(Integer propertyNo, String propertyNameAndKana, String userAndBranch,
			String owner, String dateCreated, String lastUpdated) {
		super();
		this.propertyNo = propertyNo;
		this.propertyNameAndKana = propertyNameAndKana;
		this.userAndBranch = userAndBranch;
		this.owner = owner;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}
	
	public Integer getPropertyNo() {
		return propertyNo;
	}
	public void setPropertyNo(Integer propertyNo) {
		this.propertyNo = propertyNo;
	}
	public String getPropertyNameAndKana() {
		return propertyNameAndKana;
	}
	public void setPropertyNameAndKana(String propertyNameAndKana) {
		this.propertyNameAndKana = propertyNameAndKana;
	}
	public String getUserAndBranch() {
		return userAndBranch;
	}
	public void setUserAndBranch(String userAndBranch) {
		this.userAndBranch = userAndBranch;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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