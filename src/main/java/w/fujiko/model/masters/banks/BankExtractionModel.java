package w.fujiko.model.masters.banks;

public class BankExtractionModel {
	
	private String bankCode;
	private String bankKana;
	private String bankName;
	private String branchCode;
	private String branchKana;
	private String branchName;
	private String dateCreated;
	private String lastUpdated;
	
	public BankExtractionModel(String bankCode, String bankKana, String bankName, String branchCode, String branchKana,
			String branchName, String dateCreated, String lastUpdated) {
		super();
		this.bankCode = bankCode;
		this.bankKana = bankKana;
		this.bankName = bankName;
		this.branchCode = branchCode;
		this.branchKana = branchKana;
		this.branchName = branchName;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankKana() {
		return bankKana;
	}

	public void setBankKana(String bankKana) {
		this.bankKana = bankKana;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchKana() {
		return branchKana;
	}

	public void setBranchKana(String branchKana) {
		this.branchKana = branchKana;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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