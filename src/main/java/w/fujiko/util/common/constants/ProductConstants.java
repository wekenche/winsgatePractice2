package w.fujiko.util.common.constants;

public class ProductConstants {
	
	public static final String LONG_PAGE_NAME = "商品分類プルーフリスト";
	public static final String SHORT_PAGE_NAME = "商品分類";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	/* HEADER CONSTANTS */
	public static final String PRODUCT_CLASS_CODE = "大分類名ＣＤ";
	public static final String PRODUCT_CLASS_NAME = "大分類名";
	public static final String PRODUCT_ITEM_CODE = "中分類名ＣＤ";
	public static final String PRODUCT_ITEM_NAME = "中分類名";
	public static final String DATE_CREATED = "登録日";
	public static final String LAST_UPDATED = "最終更新日";
	
	//  [大分類ＣＤ, 大分類名, 中分類ＣＤ, 中分類名, 登録日, 最終更新日] 
	
	/* EXTRACTED MODEL FIELDS CONSTANTS*/
	public static final String FIELD_PRODUCT_CLASS_CODE = "productCode";
	public static final String FIELD_PRODUCT_CLASS_NAME = "productName";
	public static final String FIELD_PRODUCT_ITEM_CODE = "productItemCode";
	public static final String FIELD_PRODUCT_ITEM_NAME = "productItemName";
	public static final String FIELD_DATE_CREATED = "dateCreated";
	public static final String FIELD_LAST_UPDATED = "lastUpdated";
	
	/* ENTITY FIELDS CONSTANTS */
	public static final String FIELD_CODE = "code";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_PRODUCT_CLASSIFICATION = "productClassification";
	public static final String FIELD_PRODUCT_CLASS_ID	 = "id";
	public static final String FIELD_IS_END = "isEnd";
	public static final String FIELD_PRODUCT_ITEMS = "productItems";
	
	/* ERROR MESSAGES */
	public static final String MSG_CONFLICT = "{ \"status\": 409, \"message\": \"この商品分類CDは既に存在します\" }";
	public static final String MSG_CONFLICT_ITEM = "{ \"status\": 409, \"message\": \"この商品分類項目CDは既に存在します\" }";
	public static final String MSG_500 = "{ \"status\": 500, \"message\": \"サーバーエラー\" }";
	
	/* FILE NAMES */
	public static final String PDF_FILE_NAME = "list.pdf";
	public static final String CSV_FILE_NAME = "list.csv";

}