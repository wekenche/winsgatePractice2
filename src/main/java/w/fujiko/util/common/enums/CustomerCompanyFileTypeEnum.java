package w.fujiko.util.common.enums;

public enum CustomerCompanyFileTypeEnum {

    TRANSACTION_APPROVAL ("transactionApproval"),
	COMPANY_SURVEY ("companySurvey"),
	TRANSACTION_REGISTER ("transactionRegister"),
	ADDRESS_CHANGE ("addressChange"),
	OTHERS ("others"),
	OLD_SALES_MANAGEMENT ("oldSalesManagement");
	 
	private final String fileType;
	 
	CustomerCompanyFileTypeEnum(String fileType) {
		this.fileType = fileType;
	}
	 
	public String getType() {
		return this.fileType;
    }
    
}