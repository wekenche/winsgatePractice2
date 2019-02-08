package w.fujiko.model.masters.companyaccounts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.banks.BankBranch;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="mst_company_account")
public class CompanyAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2140973260849629016L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "mst_company_account_id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_bank_branch_id", insertable=true, updatable = true)
	private BankBranch bankBranch;
	
	@Column(name="deposit_type", columnDefinition = "tinyint")
	private Short depositType;
	
	@Column(name="account_number", columnDefinition = "int")
	private Integer accountNumber;
	
	@Column(name="company_number", columnDefinition = "bigint")
	private Long companyNumber;
	
	@Column(name="company_name_kana", columnDefinition = "nvarchar(40)")
	private String companyNameKana;
	
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

	public BankBranch getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(BankBranch bankBranch) {
		this.bankBranch = bankBranch;
	}

	public Short getDepositType() {
		return depositType;
	}

	public void setDepositType(Short depositType) {
		this.depositType = depositType;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCompanyNameKana() {
		return companyNameKana;
	}

	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
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