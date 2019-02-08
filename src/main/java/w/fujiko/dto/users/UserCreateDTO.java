package w.fujiko.dto.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8672244999906508860L;
	
	private Integer id;
	
	@NotNull
	private Short code;
	
	@NotNull
	private String username;
	
	private String usernameKana;
	
	private String password;
	
	private Boolean isResigned;
	
	@JsonProperty("departmentId")
	private Integer departmentId;
	
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

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
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