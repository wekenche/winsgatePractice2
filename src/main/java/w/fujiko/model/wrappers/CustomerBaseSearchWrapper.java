/**
 * 
 */
package w.fujiko.model.wrappers;

import w.fujiko.model.masters.customers.CustomerDepartment;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;
import w.fujiko.model.masters.customers.CustomerGroup;

/**
 * 
 * @author yagami
 *
 */

public class CustomerBaseSearchWrapper {
	
	/**
	 * 
	 */
    private Integer id;
    
    private String code;
    
    private CustomerGroup group;
    
    private String name;

    private CustomerDepartment customerDepartment;

    private CustomerDepartmentUser departmentUser;

    public CustomerBaseSearchWrapper(Integer id,
                                     String code,
                                     CustomerGroup group,
                                     String name,
                                     CustomerDepartment customerDepartment,
                                     CustomerDepartmentUser departmentUser){
        this.id = id;
        this.code = code;
        this.group = group;
        this.name = name;
        this.customerDepartment = customerDepartment;
        this.departmentUser = departmentUser;
    }
    
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

	public CustomerGroup getGroup() {
		return group;
	}

	public void setGroup(CustomerGroup group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
    }
    
    public CustomerDepartment getCustomerDepartment() {
		return customerDepartment;
	}

	public void setCustomerDepartment(CustomerDepartment customerDepartment) {
		this.customerDepartment = customerDepartment;
    }
    
    public CustomerDepartmentUser getDepartmentUser() {
		return departmentUser;
	}

	public void setDepartmentUser(CustomerDepartmentUser departmentUser) {
		this.departmentUser = departmentUser;
	}
}