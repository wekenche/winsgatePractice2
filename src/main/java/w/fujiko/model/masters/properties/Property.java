package w.fujiko.model.masters.properties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="mst_property")
public class Property  implements Serializable {

	private static final long serialVersionUID = 6009241457571532885L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_property_id")
	private Integer id;
	
	@Column(name="property_no", columnDefinition = "int", unique=true)
	private Integer propertyNo;
	
	@Column(name="property_kana", columnDefinition = "nvarchar(40)")
	private String propertyKana;
	
	@Column(name="property_name", columnDefinition = "nvarchar(40)")
	private String propertyName;
	
	@Column(name="owner", columnDefinition = "nvarchar(40)")
	private String owner;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="registration_officer_id", insertable=true, updatable = true)
	private User registrationOfficer;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="branch_id", insertable=true, updatable = true)
	private Branch branch;
	
	@Column(name="deleted_flg", columnDefinition = "bit default 0")
	private Boolean deletedFLG;
	
	@Column(name="remarks",columnDefinition="ntext")
	private String remarks;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User createdBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program createdAt;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(Integer propertyNo) {
		this.propertyNo = propertyNo;
	}

	public String getPropertyKana() {
		return propertyKana;
	}

	public void setPropertyKana(String propertyKana) {
		this.propertyKana = propertyKana;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public User getRegistrationOfficer() {
		return registrationOfficer;
	}

	public void setRegistrationOfficer(User registrationOfficer) {
		this.registrationOfficer = registrationOfficer;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Boolean getDeletedFLG() {
		return deletedFLG;
	}

	public void setDeletedFLG(Boolean deletedFLG) {
		this.deletedFLG = deletedFLG;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}

}