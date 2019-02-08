package w.fujiko.dto.properties;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyUpdateDTO implements Serializable {

	private static final long serialVersionUID = -1090324395272820339L;
	
	private Integer id;
	
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
	
	@JsonProperty("updatedBy")
	@NotNull
	private Integer updatedById;

	@JsonProperty("updatedAt")
	@NotNull
	private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}

}