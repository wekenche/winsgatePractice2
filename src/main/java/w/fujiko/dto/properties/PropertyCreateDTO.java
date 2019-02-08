package w.fujiko.dto.properties;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyCreateDTO implements Serializable {

	private static final long serialVersionUID = -2546089917221573085L;
	
	private Integer id;
	
	private Integer propertyNo;
	
	private String propertyKana;
	
	@NotNull
	private String propertyName;
	
	@NotNull
	private String owner;
	
	@JsonProperty("registrationOfficer")
	@NotNull
	private Integer registrationOfficerId;
	
	@JsonProperty("branch")
	@NotNull
	private Integer branchId;
	
	@NotNull
	private Boolean deletedFLG;
	
	private String remarks;
	
	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;

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

	public Integer getRegistrationOfficerId() {
		return registrationOfficerId;
	}

	public void setRegistrationOfficerId(Integer registrationOfficerId) {
		this.registrationOfficerId = registrationOfficerId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
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