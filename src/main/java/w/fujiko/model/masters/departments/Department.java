/**
 * 
 */
package w.fujiko.model.masters.departments;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.users.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_u_department")
public class Department extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @NotNull
    @ManyToOne
	@JoinColumn(name="mst_u_dept_branch_id", insertable=true, updatable = true)
    private Branch branch;

    @ManyToOne
	@JoinColumn(name="mst_u_parent_dept_id", nullable=true, insertable=true, updatable = true)
	@JsonBackReference	
    private Department parentDepartment;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="parentDepartment")
	@JsonManagedReference		
    private List<Department> subDepartments;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="department")
	@JsonManagedReference		
	private List<User> users;
	
	@NotNull
	@Column(name="mst_u_dept_cd", columnDefinition = "varchar(4)",updatable=false)
	private String code;

	@NotNull
	@Column(name="dept_name", columnDefinition = "nvarchar(100)")
    private String name;
    
	@Column(name="dept_name_kana", columnDefinition = "nvarchar(100)")
    private String nameKana;

	@Column(name="dept_shortname", columnDefinition = "nvarchar(50)")
    private String shortName;
    
	@Column(name="dept_sales_flg", columnDefinition = "bit default 0")
    private Boolean salesFlag;

	@Column(name="dept_order", columnDefinition = "int")
    private Integer sequence;

    @Column(name="dept_postalcode", columnDefinition = "nvarchar(20)")
    private String postalCode;

    @Column(name="dept_address1", columnDefinition = "nvarchar(250)")
    private String address1;
    
    @Column(name="dept_address2", columnDefinition = "nvarchar(250)")
    private String address2;
    
    @Column(name="dept_telno", columnDefinition = "nvarchar(50)")
    private String telephoneNumber;

    @Column(name="dept_faxno", columnDefinition = "nvarchar(50)")
    private String faxNumber;

    @Column(name="dept_receipt_seqno", columnDefinition = "nvarchar(50)")
    private String receiptSequenceNumber;

    @Column(name="dept_receipt_symbol", columnDefinition = "nvarchar(30)")
    private String receiptSymbol;

    @Column(name="is_end", columnDefinition = "bit default 0")
    private Boolean isEnd;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public List<Department> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(List<Department> subDepartments) {
		this.subDepartments = subDepartments;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

	public Boolean getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(Boolean salesFlag) {
		this.salesFlag = salesFlag;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getReceiptSequenceNumber() {
		return receiptSequenceNumber;
	}

	public void setReceiptSequenceNumber(String receiptSequenceNumber) {
		this.receiptSequenceNumber = receiptSequenceNumber;
	}

	public String getReceiptSymbol() {
		return receiptSymbol;
	}

	public void setReceiptSymbol(String receiptSymbol) {
		this.receiptSymbol = receiptSymbol;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

}