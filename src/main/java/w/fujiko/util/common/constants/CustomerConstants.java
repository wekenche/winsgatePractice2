package w.fujiko.util.common.constants;

public class CustomerConstants {
	
	public static final String FIELD_IS_END = "isEnd";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_GROUP = "group";
	public static final String FIELD_ID = "id";

	/* ERROR MESSAGES */
	public static final String MSG_CONFLICT_CUSTOMER = "{ \"status\": 409, \"message\": \"この得意先拠点CDは既に存在します\" }";
	public static final String MSG_CONFLICT_GROUP = "{ \"status\": 409, \"message\": \"この得意先グループCDは既に存在します\" }";
	public static final String MSG_CONFLICT_CUSTOMER_DEPARTMENT = "{ \"status\": 409, \"message\": \"この得意先部課CDは既に存在します\" }";
	public static final String MSG_500 = "{ \"status\" : 500, \"message\": \"サーバーエラー \"}";
}