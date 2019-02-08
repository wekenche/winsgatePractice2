/**
 * 
 */
package w.fujiko.model.masters.customers;

import java.io.Serializable;

import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;

import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_cst_department")
public class CustomerDepartment extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@NotNull
    @Column(name="mst_cst_department_cd", columnDefinition = "smallint", updatable = false)
    private int code;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="mst_cst_base_id", columnDefinition = "int", insertable=true,updatable=true)
	private CustomerBase base;

	@NotNull
    @Column(name="name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name="industry", columnDefinition = "nvarchar(50)")
    private String industry;
    
    @Column(name="phone_number", columnDefinition = "varchar(30)")
    private String phoneNumber;

    @Column(name="extension", columnDefinition = "nvarchar(30)")
    private String extension;

    @Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="customerDepartment")
	@JsonManagedReference
    private Set<CustomerDepartmentUser> customerDepartmentUsers=new HashSet<>();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
    }

	public CustomerBase getBase() {
		return base;
	}

	public void setBase(CustomerBase base) {
		this.base = base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	} 

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}   

	public Set<CustomerDepartmentUser> getCustomerDepartmentUsers() {
        return customerDepartmentUsers;
    }

    public void setCustomerDepartmentUsers(Set<CustomerDepartmentUser> customerDepartmentUser) {
        this.customerDepartmentUsers = customerDepartmentUsers;
    }
}