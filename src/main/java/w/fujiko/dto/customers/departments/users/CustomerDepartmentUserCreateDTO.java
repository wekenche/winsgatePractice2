/**
 * 
 */
package w.fujiko.dto.customers.departments.users;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class CustomerDepartmentUserCreateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @JsonProperty("customerDepartmentId")
    @NotNull
    private int customerDepartmentId;
    
    @JsonProperty("userId")
    @NotNull
	private int userId;

    @JsonIgnore
    private Date dateCreated=new Date();

    @JsonProperty("createdBy")
    @NotNull
    private Integer createdById;

    @JsonProperty("createdAt")
    @NotNull
    private String createdAtId;
    
	public int getCustomerDepartmentId() {
		return customerDepartmentId;
	}

	public void setCustomerDepartmentId(int customerDepartmentId) {
		this.customerDepartmentId = customerDepartmentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDateCreated() {
		return dateCreated;
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