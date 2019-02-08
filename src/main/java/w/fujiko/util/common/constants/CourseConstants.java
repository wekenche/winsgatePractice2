package w.fujiko.util.common.constants;

public class CourseConstants {
	
	/* ENTITY FIELDS CONSTANTS */
	public static final String FIELD_ID = "id";
	public static final String FIELD_COURSE = "course";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_IS_END = "isEnd";
	
	/* ERROR MESSAGES */
	public static final String MSG_CONFLICT_COURSE = "{ \"status\": 409, \"message\": \"この科目CDは既に存在します\" }";
	public static final String MSG_CONFLICT_COURSE_AUX = "{ \"status\": 409, \"message\": \"この補助科目CDは既に存在します\" }";
	public static final String MSG_500 = "{ \"status\" : 500, \"message\": \"サーバーエラー \"}";

}
