package w.fujiko.util.common.constants;

public class CompanyAccountConstants {
	
	public static final String LONG_PAGE_NAME = "自社口座プルーフリスト";
	public static final String SHORT_PAGE_NAME = "自社口座";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	public static final String ID = "id";
	
	/* HEADER CONSTANTS */
	public static final String COMPANY_ACCOUNT_NO = "自社口座No";
	public static final String BANK = "取引銀行";
	public static final String BANK_CODE = "取引銀行CD";
	public static final String BANK_NAME = "取引銀行名";
	public static final String BRANCH = "取引支店";
	public static final String BRANCH_CODE = "取引支店CD";
	public static final String BRANCH_NAME = "取引支店名";
	public static final String DEPOSIT_TYPE = "預金種別";
	public static final String ACCOUNT_NO = "口座番号";
	public static final String COMPANY_NO = "会社番号";
	public static final String COMPANY_NAME_KANA = "会社名ｶﾅ";
	public static final String DATE_CREATED = "登録日";
	public static final String LAST_UPDATED = "最終更新日";
	
	/* EXTRACTED MODEL FIELDS CONSTANTS */
	public static final String FIELD_COMPANY_ACCOUNT_NO = "companyAccountNo";
	public static final String FIELD_BANK = "bank";
	public static final String FIELD_BANK_CODE = "bankCode";
	public static final String FIELD_BANK_NAME = "bankName";
	public static final String FIELD_BRANCH = "branch";
	public static final String FIELD_BRANCH_CODE = "branchCode";
	public static final String FIELD_BRANCH_NAME = "branchName";
	public static final String FIELD_DEPOSIT_TYPE = "depositType";
	public static final String FIELD_ACCOUNT_NO = "accountNo";
	public static final String FIELD_COMPANY_NO = "companyNo";
	public static final String FIELD_COMPANY_NAME_KANA = "companyNameKana";
	public static final String FIELD_DATE_CREATED = "dateCreated";
	public static final String FIELD_LAST_UPDATED = "lastUpdated";
	
	/* DEPOSIT TYPE VALUES */
	public static final String USUAL = "１ 普  通";
	public static final String CURRENT = "２ 当  座";
	public static final String OTHER = "９　その他";
	
	public static final String[] CSV_HEADER_LIST = {
			COMPANY_ACCOUNT_NO,
			BANK_CODE,
			BANK_NAME,
			BRANCH_CODE,
			BRANCH_NAME,
			DEPOSIT_TYPE,
			ACCOUNT_NO,
			COMPANY_NO,
			COMPANY_NAME_KANA,
			DATE_CREATED,
			LAST_UPDATED
		};
	
	public static final String[] CSV_FIELD_LIST = {
			FIELD_COMPANY_ACCOUNT_NO,
			FIELD_BANK_CODE,
			FIELD_BANK_NAME,
			FIELD_BRANCH_CODE,
			FIELD_BRANCH_NAME,
			FIELD_DEPOSIT_TYPE,
			FIELD_ACCOUNT_NO,
			FIELD_COMPANY_NO,
			FIELD_COMPANY_NAME_KANA,
			FIELD_DATE_CREATED,
			FIELD_LAST_UPDATED
		};
	
	public static final String[] PDF_HEADER_LIST = {
			COMPANY_ACCOUNT_NO,
			BANK,
			BRANCH,
			DEPOSIT_TYPE,
			ACCOUNT_NO,
			COMPANY_NO,
			COMPANY_NAME_KANA,
			DATE_CREATED,
			LAST_UPDATED
		};
	
	public static final String[] PDF_FIELD_LIST = {
			FIELD_COMPANY_ACCOUNT_NO,
			FIELD_BANK,
			FIELD_BRANCH,
			FIELD_DEPOSIT_TYPE,
			FIELD_ACCOUNT_NO,
			FIELD_COMPANY_NO,
			FIELD_COMPANY_NAME_KANA,
			FIELD_DATE_CREATED,
			FIELD_LAST_UPDATED
		};
	
	/* ERROR MESSAGES */
	public static final String MSG_CONFLICT = "{ \"status\": 409, \"message\": \"この自社口座Noは既に存在します\" }";
	public static final String MSG_500 = "{ \"status\": 500, \"message\": \"サーバーエラー\" }";
	
	/* FILE NAMES */
	public static final String PDF_FILE_NAME = "list.pdf";
	public static final String CSV_FILE_NAME = "list.csv";

}