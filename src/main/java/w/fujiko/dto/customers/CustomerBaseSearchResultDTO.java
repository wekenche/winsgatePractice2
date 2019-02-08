/**
 * 
 */
package w.fujiko.dto.customers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.customers.departments.CustomerDepartmentDefaultDTO;
import w.fujiko.dto.customers.departments.users.CustomerDepartmentUserDefaultDTO;

/**
 * 
 * @author yagami
 *
 */
public class CustomerBaseSearchResultDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
    
    @JsonProperty("id")
    private Integer id;

	@JsonProperty("code")
    private String code;

    @JsonProperty("name")    
    private String name;
	
	@JsonProperty("customerGroup")
    private CustomerGroupDefaultDTO group;
    
    @JsonProperty("customerDepartment")
    private CustomerDepartmentDefaultDTO customerDepartment;
    
    @JsonProperty("departmentUser")
	private CustomerDepartmentUserDefaultDTO departmentUser;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
    }
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerGroupDefaultDTO getGroup() {
		return group;
	}

	public void setGroup(CustomerGroupDefaultDTO group) {
		this.group = group;
    }
    
    public CustomerDepartmentDefaultDTO getCustomerDepartment() {
		return customerDepartment;
	}

	public void setCustomerDepartment(CustomerDepartmentDefaultDTO customerDepartment) {
		this.customerDepartment = customerDepartment;
    }
    
    public CustomerDepartmentUserDefaultDTO getDepartmentUser() {
		return departmentUser;
	}

	public void setDepartmentUser(CustomerDepartmentUserDefaultDTO departmentUser) {
		this.departmentUser = departmentUser;
    }

}