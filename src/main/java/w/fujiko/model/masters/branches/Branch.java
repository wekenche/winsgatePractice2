/**
 * 
 */
package w.fujiko.model.masters.branches;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import w.fujiko.model.masters.Warehouse;
import w.fujiko.model.masters.departments.Department;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "id")
@Entity
@Table(name="mst_u_branch")
public class Branch extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@NotNull
	@Column(name="mst_u_branch_cd", columnDefinition = "int",updatable=false)
	private Integer code;
	
	@NotNull
	@Column(name="mst_u_branch_name", columnDefinition = "nvarchar(100)")
    private String name;

	@Column(name="branch_name_kana", columnDefinition = "nvarchar(100)")
    private String nameKana;

	@Column(name="branch_shortname", columnDefinition = "nvarchar(50)")
    private String shortName;
    
	@Column(name="branch_sales_flg", columnDefinition = "bit default 0")
    private Boolean salesFlag;

	@Column(name="branch_order", columnDefinition = "int")
    private Integer sequence;
    
    @Column(name="branch_postalcode", columnDefinition = "nvarchar(20)")
    private String postalCode;
    
    @Column(name="branch_address1", columnDefinition = "nvarchar(250)")
    private String address1;

    @Column(name="branch_address2", columnDefinition = "nvarchar(250)")
    private String address2;
    
    @Column(name="branch_telno", columnDefinition = "nvarchar(50)")
    private String telephoneNumber;

    @Column(name="branch_faxno", columnDefinition = "nvarchar(50)")
    private String faxNumber;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="mst_u_branch_warehouse_id",insertable=true, updatable = true)
	private Warehouse warehouse;

    @Column(name="branch_receipt_seqno", columnDefinition = "nvarchar(50)")
    private String receiptSequenceNumber;

    @Column(name="branch_receipt_symbol", columnDefinition = "nvarchar(30)")
    private String receiptSymbol;

	@Column(name="independent_flg", columnDefinition = "bit default 0")
	private Boolean isIndependent;

    @Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="branch",cascade = CascadeType.ALL)	
	@OrderBy(value = "ID ASC")
    private Set<Department> departments = new HashSet<>();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
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

	public Boolean getIsIndependent() {
		return isIndependent;
	}

	public void setIsIndependent(Boolean isIndependent) {
		this.isIndependent = isIndependent;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
}