package w.fujiko.model.masters;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

/**
 * 
 * @author johnl
 *
 */

@Entity
@Table(name="mst_warehouse")
public class Warehouse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7266566357830949853L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_whs_id")
	private Integer id;
	
	@Column(name="whs_cd", columnDefinition = "bigint", updatable = false)
	private Long code;
	
	@Column(name="whs_name", columnDefinition = "nvarchar(30)")
	private String name;
	
	@Column(name="whs_kana", columnDefinition = "nvarchar(30)")
	private String kana;
	
	@Column(name="whs_shortname", columnDefinition = "nvarchar(30)")
	private String shortName;
	
	@Column(name="whs_postal_code", columnDefinition = "varchar(15)")
	private String postalCode;
	
	@Column(name="whs_address_ichi", columnDefinition = "nvarchar(30)")
	private String address1;
	
	@Column(name="whs_address_ni", columnDefinition = "nvarchar(30)")
	private String address2;
	
	@Column(name="whs_phone_number", columnDefinition = "varchar(15)")
	private String phoneNumber;
	
	@Column(name="whs_fax_number", columnDefinition = "varchar(15)")
	private String faxNumber;

	@Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="warehouse",cascade = CascadeType.ALL)	
	@OrderBy(value = "ID ASC")
    private Set<Branch> branches = new HashSet<>();
    
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User createdBy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program createdAt;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}

}