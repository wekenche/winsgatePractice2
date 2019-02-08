package w.fujiko.dto.suppliers.departments.users;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierDepartmentUserCreateDTO implements Serializable {

	private static final long serialVersionUID = 9003600282040546039L;
	
	@JsonProperty("supplierDepartmentId")
	@NotNull
	private Integer supplierDepartmentId;
	
	@JsonProperty("userId")
	@NotNull
	private Integer userId;
	
	@JsonIgnore
	private Date dateCreated=new Date();
	
	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;
	
	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;

	public Integer getSupplierDepartmentId() {
		return supplierDepartmentId;
	}

	public void setSupplierDepartmentId(Integer supplierDepartmentId) {
		this.supplierDepartmentId = supplierDepartmentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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