package w.fujiko.dto.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3736640151164470995L;
	
	@NotNull
	private Integer id;
	
	@NotNull
	private String username;
	
	private String usernameKana;
	
	private String password;
	
	private Boolean isResigned;
	
	@JsonProperty("departmentId")
	private Integer departmentId;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameKana() {
		return usernameKana;
	}

	public void setUsernameKana(String usernameKana) {
		this.usernameKana = usernameKana;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsResigned() {
		return isResigned;
	}

	public void setIsResigned(Boolean isResigned) {
		this.isResigned = isResigned;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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