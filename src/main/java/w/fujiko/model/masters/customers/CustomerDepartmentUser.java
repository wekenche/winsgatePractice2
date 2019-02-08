/**
 * 
 */
package w.fujiko.model.masters.customers;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fte.api.Defaults;
import w.fujiko.model.masters.users.User;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_cst_userdept")
public class CustomerDepartmentUser extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="mst_cst_department_id", columnDefinition = "int", insertable=true,updatable=true)
    private CustomerDepartment customerDepartment;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="mst_u_user_userdeptid", columnDefinition = "int", insertable=true,updatable=true)
	private User user;

	public CustomerDepartment getCustomerDepartment() {
		return customerDepartment;
	}

	public void setCustomerDepartment(CustomerDepartment customerDepartment) {
		this.customerDepartment = customerDepartment;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}