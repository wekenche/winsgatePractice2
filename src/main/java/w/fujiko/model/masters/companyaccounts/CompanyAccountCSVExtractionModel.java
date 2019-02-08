package w.fujiko.model.masters.companyaccounts;

public class CompanyAccountCSVExtractionModel {

	private Integer companyAccountNo;
	private Integer bankCode;
	private String bankName;
	private Integer branchCode;
	private String branchName;
	private String depositType;
	private Integer accountNo;
	private Long companyNo;
	private String companyNameKana;
	private String dateCreated;
	private String lastUpdated;
	
	public CompanyAccountCSVExtractionModel(Integer companyAccountNo, Integer bankCode, String bankName,
			Integer branchCode, String branchName, String depositType, Integer accountNo, Long companyNo,
			String companyNameKana, String dateCreated, String lastUpdated) {
		super();
		this.companyAccountNo = companyAccountNo;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.depositType = depositType;
		this.accountNo = accountNo;
		this.companyNo = companyNo;
		this.companyNameKana = companyNameKana;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

	public Integer getCompanyAccountNo() {
		return companyAccountNo;
	}

	public void setCompanyAccountNo(Integer companyAccountNo) {
		this.companyAccountNo = companyAccountNo;
	}

	public Integer getBankCode() {
		return bankCode;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public Long getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Long companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyNameKana() {
		return companyNameKana;
	}

	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
}