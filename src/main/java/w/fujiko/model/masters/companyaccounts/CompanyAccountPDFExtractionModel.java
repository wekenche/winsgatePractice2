package w.fujiko.model.masters.companyaccounts;

public class CompanyAccountPDFExtractionModel {
	
	private Integer companyAccountNo;
	private String bank;
	private String branch;
	private String depositType;
	private String accountNo;
	private String companyNo;
	private String companyNameKana;
	private String dateCreated;
	private String lastUpdated;
	
	public CompanyAccountPDFExtractionModel(Integer companyAccountNo, String bank, String branch, String depositType,
			String accountNo, String companyNo, String companyNameKana, String dateCreated, String lastUpdated) {
		super();
		this.companyAccountNo = companyAccountNo;
		this.bank = bank;
		this.branch = branch;
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDepositType() {
		return depositType;
	}
	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
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