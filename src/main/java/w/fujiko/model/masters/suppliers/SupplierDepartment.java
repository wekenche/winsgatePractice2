package w.fujiko.model.masters.suppliers;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fte.api.Defaults;

@Entity
@Table(name="mst_splr_department")
public class SupplierDepartment extends Defaults implements Serializable {

	private static final long serialVersionUID = 5351544604158108909L;
	
	@NotNull
    @Column(name="mst_splr_department_cd", columnDefinition = "smallint", updatable = false)
    private Integer code;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="mst_splr_base_id", columnDefinition = "int", insertable=true,updatable=true)
	private SupplierBase base;

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
    
    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="supplierDepartment")
	@JsonManagedReference
    private Set<SupplierDepartmentUser> supplierDepartmentUsers = new HashSet<>();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public SupplierBase getBase() {
		return base;
	}

	public void setBase(SupplierBase base) {
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

	public Set<SupplierDepartmentUser> getSupplierDepartmentUsers() {
		return supplierDepartmentUsers;
	}

	public void setSupplierDepartmentUsers(Set<SupplierDepartmentUser> supplierDepartmentUsers) {
		this.supplierDepartmentUsers = supplierDepartmentUsers;
	}

}