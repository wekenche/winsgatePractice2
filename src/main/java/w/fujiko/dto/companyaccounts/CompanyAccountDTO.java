package w.fujiko.dto.companyaccounts;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.banks.BankBranchWithDetailedBankDTO;

public class CompanyAccountDTO implements Serializable {

	private static final long serialVersionUID = 8835436991579906519L;
	
	private Integer id;
	
	private BankBranchWithDetailedBankDTO bankBranch;
	
	private Short depositType;
	
	private Integer accountNumber;
	
	private Long companyNumber;
	
	private String companyNameKana;
	
	private Date dateCreated;

	private Date dateUpdated;

	@JsonProperty("createdBy")
	private Integer createdById;

	@JsonProperty("updatedBy")
	private Integer updatedById;

	@JsonProperty("createdAt")
	private String createdAtId;

	@JsonProperty("updatedAt")
	private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BankBranchWithDetailedBankDTO getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(BankBranchWithDetailedBankDTO bankBranch) {
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}
	
}