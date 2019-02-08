/**
 * 
 */
package w.fujiko.dto.users;

import java.io.Serializable;
import java.util.Date;

import w.fujiko.dto.departments.DepartmentDTODefault;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("id")
	private Integer id;
	
	@JsonProperty("code")
	private Short code;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("usernameKana")
	private String usernameKana;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("isResigned")
	private Boolean isResigned;
	
	@JsonProperty("department")
	private DepartmentDTODefault department;

    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    @JsonProperty("createdBy")
    private Integer createdBy;

    @JsonProperty("updatedBy")
    private Integer updatedBy;

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

	public DepartmentDTODefault getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTODefault department) {
		this.department = department;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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