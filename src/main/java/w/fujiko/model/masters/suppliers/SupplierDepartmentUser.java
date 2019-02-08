package w.fujiko.model.masters.suppliers;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fte.api.Defaults;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="mst_splr_userdept")
public class SupplierDepartmentUser extends Defaults implements Serializable {

	private static final long serialVersionUID = -290639216196182691L;
	
	@NotNull
    @ManyToOne
    @JoinColumn(name="mst_splr_department_id", columnDefinition = "int", insertable=true,updatable=true)
    private SupplierDepartment supplierDepartment;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="mst_u_splr_userdeptid", columnDefinition = "int", insertable=true,updatable=true)
	private User user;

	public SupplierDepartment getSupplierDepartment() {
		return supplierDepartment;
	}

	public void setSupplierDepartment(SupplierDepartment supplierDepartment) {
		this.supplierDepartment = supplierDepartment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}