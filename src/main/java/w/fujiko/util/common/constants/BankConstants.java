package w.fujiko.util.common.constants;

public class BankConstants {
	
	public static final String LONG_PAGE_NAME = "銀行プルーフリスト";
	public static final String SHORT_PAGE_NAME = "銀行";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	/* HEADER CONSTANTS */
	public static final String BANK_CODE = "銀行ＣＤ";
	public static final String BANK_KANA = "銀行名カナ";
	public static final String BANK_NAME = "銀行名";
	public static final String BANK_BRANCH_CODE = "支店ＣＤ";
	public static final String BANK_BRANCH_KANA = "支店名カナ";
	public static final String BANK_BRANCH_NAME = "支店名";
	public static final String DATE_CREATED = "登録日";
	public static final String LAST_UPDATED = "最終更新日";
	
	/* EXTRACTED MODEL FIELDS CONSTANTS*/
	public static final String FIELD_BANK_CODE = "bankCode";
	public static final String FIELD_BANK_KANA = "bankKana";
	public static final String FIELD_BANK_NAME = "bankName";
	public static final String FIELD_BRANCH_CODE = "branchCode";
	public static final String FIELD_BRANCH_KANA = "branchKana";
	public static final String FIELD_BRANCH_NAME = "branchName";
	public static final String FIELD_DATE_CREATED = "dateCreated";
	public static final String FIELD_LAST_UPDATED = "lastUpdated";
	
	/* ENTITY FIELDS CONSTANTS */
	public static final String FIELD_BRANCHES = "branches";
	public static final String FIELD_IS_END = "isEnd";
	public static final String FIELD_BANK_ID = "id";
	public static final String FIELD_BANK = "bank";
	public static final String FIELD_BANK_BRANCH_CODE = "bankBranchCode";
	public static final String FIELD_BANK_BRANCH_NAME = "bankBranchName";
	
	/* ERROR MESSAGES */
	public static final String MSG_CONFLICT_BANK = "{ \"status\": 409, \"message\": \"この銀行CDは既に存在します\" }";
	public static final String MSG_CONFLICT_BRANCH = "{ \"status\": 409, \"message\": \"この銀行支店CDは既に存在します\" }";
	public static final String MSG_500 = "{ \"status\" : 500, \"message\": \"サーバーエラー \"}";
	
	/* FILE NAMES */
	public static final String PDF_FILE_NAME = "list.pdf";
	public static final String CSV_FILE_NAME = "list.csv";

}