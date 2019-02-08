package w.fujiko.dto.companyaccounts;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyAccountUpdateDTO implements Serializable {

	private static final long serialVersionUID = -2299876573119926073L;
	
	@NotNull
	private Integer id;
	
	@JsonProperty("bankBranchId")
	@NotNull
	private Integer bankBranchId;
	
	private Short depositType;
	
	private Integer accountNumber;
	
	private Long companyNumber;
	
	private String companyNameKana;
	
	@JsonProperty("updatedBy")
	@NotNull
	private Integer updatedById;

	@JsonProperty("updatedAt")
	@NotNull
	private String updatedAtId;

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

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}

}