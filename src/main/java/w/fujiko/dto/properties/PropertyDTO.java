package w.fujiko.dto.properties;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.branches.BranchDTODefault;
import w.fujiko.dto.users.UserDTO;

public class PropertyDTO implements Serializable {

	private static final long serialVersionUID = -3694401684135232199L;
	
	private Integer id;
	
	private Integer propertyNo;
	
	private String propertyKana;
	
	private String propertyName;
	
	private String owner;
	
	private UserDTO registrationOfficer;
	
	private BranchDTODefault branch;
	
	private Boolean deletedFLG;
	
	private String remarks;
	
	private Long propertyCount;
	
	private Date dateCreated;

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

	public UserDTO getRegistrationOfficer() {
		return registrationOfficer;
	}

	public void setRegistrationOfficer(UserDTO registrationOfficer) {
		this.registrationOfficer = registrationOfficer;
	}

	public BranchDTODefault getBranch() {
		return branch;
	}

	public void setBranch(BranchDTODefault branch) {
		this.branch = branch;
	}

	public Boolean getDeletedFLG() {
		return deletedFLG;
	}

	public void setDeletedFLG(Boolean deletedFLG) {
		this.deletedFLG = deletedFLG;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getPropertyCount() {
		return propertyCount;
	}

	public void setPropertyCount(Long propertyCount) {
		this.propertyCount = propertyCount;
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