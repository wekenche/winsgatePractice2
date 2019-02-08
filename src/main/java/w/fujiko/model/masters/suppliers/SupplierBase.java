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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fte.api.Defaults;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.payments.Payment;

@Entity
@Table(name="mst_splr_base")
public class SupplierBase extends Defaults implements Serializable {

	private static final long serialVersionUID = 8044899110017740813L;
	
	@NotNull
    @Column(name="mst_splr_base_cd", columnDefinition = "varchar(7)", updatable = false)
    private String code;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="mst_splr_group_id", columnDefinition = "int", insertable=true,updatable=true)
	private SupplierGroup group;

	@NotNull
	@ManyToOne
    @JoinColumn(name="mst_payment_id", columnDefinition = "int", insertable=true,updatable=true)
    private Payment payment;

	@NotNull
    @Column(name="name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name="kana", columnDefinition = "nvarchar(50)")
    private String nameKana;

    @Column(name="short_name", columnDefinition = "nvarchar(25)")
    private String shortName;
    
    @ManyToOne
    @JoinColumn(name="jurisdiction", columnDefinition = "int", insertable=true,updatable=true,nullable=true)
    private Branch branch;

    @Column(name="postal_code", columnDefinition = "nvarchar(20)")
    private String postalCode;

    @Column(name="address1", columnDefinition = "nvarchar(250)")
    private String address1;
    
    @Column(name="address2", columnDefinition = "nvarchar(250)")
    private String address2;

    @Column(name="address3", columnDefinition = "nvarchar(250)")
    private String address3;
    
    @Column(name="phone_number1", columnDefinition = "nvarchar(30)")
    private String phoneNumber1;

    @Column(name="phone_number2", columnDefinition = "nvarchar(30)")
    private String phoneNumber2;

    @Column(name="phone_number3", columnDefinition = "nvarchar(30)")
    private String phoneNumber3;

    @Column(name="fax_number1", columnDefinition = "nvarchar(30)")
    private String faxNumber1;

    @Column(name="fax_number2", columnDefinition = "nvarchar(30)")
    private String faxNumber2;

    @Email
    @Column(name="email", columnDefinition = "nvarchar(30)")
    private String email;

    @Column(name="url", columnDefinition = "nvarchar(300)")
    private String url;

    @Column(name="single", columnDefinition = "bit default 0")
    private Boolean single;

    @Column(name="contract", columnDefinition = "nvarchar(250)")
    private String contract;

    @Column(name="memo", columnDefinition = "nvarchar(MAX)")
    private String memo;

    @Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
    
    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="base")
	@JsonManagedReference
    private Set<SupplierDepartment> supplierDepartments = new HashSet<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SupplierGroup getGroup() {
		return group;
	}

	public void setGroup(SupplierGroup group) {
		this.group = group;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	public String getFaxNumber1() {
		return faxNumber1;
	}

	public void setFaxNumber1(String faxNumber1) {
		this.faxNumber1 = faxNumber1;
	}

	public String getFaxNumber2() {
		return faxNumber2;
	}

	public void setFaxNumber2(String faxNumber2) {
		this.faxNumber2 = faxNumber2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getSingle() {
		return single;
	}

	public void setSingle(Boolean single) {
		this.single = single;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Set<SupplierDepartment> getSupplierDepartments() {
		return supplierDepartments;
	}

	public void setSupplierDepartments(Set<SupplierDepartment> supplierDepartments) {
		this.supplierDepartments = supplierDepartments;
	}

}