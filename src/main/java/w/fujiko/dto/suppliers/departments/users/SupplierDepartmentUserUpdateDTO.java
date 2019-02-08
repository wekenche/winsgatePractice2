package w.fujiko.dto.suppliers.departments.users;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierDepartmentUserUpdateDTO implements Serializable {
	
	private static final long serialVersionUID = -8217454496863681608L;
	
	@JsonProperty("id")
	@NotNull
	private Integer id;
	
	@JsonProperty("supplierDepartmentId")
	@NotNull
	private Integer supplierDepartmentId;
	
	@JsonProperty("userId")
	@NotNull
	private Integer userId;
	
	@JsonIgnore
	private Date dateUpdated=new Date();
	
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
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