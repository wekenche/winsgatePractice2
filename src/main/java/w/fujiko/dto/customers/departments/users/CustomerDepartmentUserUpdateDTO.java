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
public class CustomerDepartmentUserUpdateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @JsonProperty("id")
    @NotNull
    private int id;
    
    @JsonProperty("customerDepartmentId")
    @NotNull
    private int customerDepartmentId;
    
    @JsonProperty("userId")
    @NotNull
	private int userId;

    @JsonIgnore
    private Date dateUpdated=new Date();

    @JsonProperty("updatedBy")
    @NotNull
    private Integer updatedById;

    @JsonProperty("updatedAt")
    @NotNull
    private String updatedAtId;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
    }
    
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

	public Date getDateUpdated() {
		return dateUpdated;
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