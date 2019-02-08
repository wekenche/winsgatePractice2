package w.fujiko.dto.companyaccounts;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyAccountCreateDTO implements Serializable {

	private static final long serialVersionUID = -2666518552613105422L;
	
	private Integer id;
	
	@JsonProperty("bankBranchId")
	@NotNull
	private Integer bankBranchId;
	
	private Short depositType;
	
	private Integer accountNumber;
	
	private Long companyNumber;
	
	private String companyNameKana;

	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBankBranchId() {
		return bankBranchId;
	}

	public void setBankBranchId(Integer bankBranchId) {
		this.bankBranchId = bankBranchId;
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

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

}