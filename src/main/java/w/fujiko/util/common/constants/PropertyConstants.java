package w.fujiko.util.common.constants;

public class PropertyConstants {
	
	public static final String PAGE_NAME = "物件プルーフリスト";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	/* HEADER CONSTANTS */
	public static final String PROPERTY_NO = "物件No";
	public static final String PROPERTY_KANA = "物件名ｶﾅ";
	public static final String PROPERTY_NAME = "物件名";
	public static final String OWNER = "オーナー";
	public static final String USER = "登録担当者";
	public static final String USER_CD = "登録担当者CD";
	public static final String USER_NAME = "登録担当者名";
	public static final String BRANCH = "管轄拠点";
	public static final String BRANCH_CD = "管轄拠点CD";
	public static final String BRANCH_NAME = "管轄拠点名";
	public static final String DATE_CREATED = "登録日";
	public static final String LAST_UPDATED = "最終更新日";
	
	/* EXTRACTED MODEL FIELDS CONSTANTS*/
	public static final String FIELD_PROPERTY_NAME_AND_KANA = "propertyNameAndKana";
	public static final String FIELD_PROPERTY_NO = "propertyNo";
	public static final String FIELD_PROPERTY_KANA = "propertyKana";
	public static final String FIELD_PROPERTY_NAME = "propertyName";
	public static final String FIELD_OWNER = "owner";
	public static final String FIELD_USER_AND_BRANCH = "userAndBranch";
	public static final String FIELD_USER_CD = "userCode";
	public static final String FIELD_BRANCH = "branch";
	public static final String FIELD_BRANCH_CD = "branchCode";
	public static final String FIELD_BRANCH_NAME = "branchName";
	public static final String FIELD_DATE_CREATED = "dateCreated";
	public static final String FIELD_LAST_UPDATED = "lastUpdated";
	
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_USER_NAME = "username";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_REG_OFFICER = "registrationOfficer";
	public static final String FIELD_DEPARTMENT = "department";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_DATE_UPDATED = "dateUpdated";
	
	/* ERROR MESSAGES */
	public static final String MSG_CANNOT_DELETE = "{ \"status\": 400, \"message\": \"この物件を削除することができません。\" }";
	public static final String MSG_500 = "{ \"status\" : 500, \"message\": \"サーバーエラー \"}";

	/* FILE NAMES */
	public static final String PDF_FILE_NAME = "list.pdf";
	public static final String CSV_FILE_NAME = "list.csv";
}