package w.fujiko.util.common.constants;

import w.fujiko.util.common.csvfileuploadextractor.BooleanChecker;
import w.fujiko.util.common.csvfileuploadextractor.Checker;
import w.fujiko.util.common.csvfileuploadextractor.DateChecker;
import w.fujiko.util.common.csvfileuploadextractor.DecimalChecker;
import w.fujiko.util.common.csvfileuploadextractor.DuplicateChecker;
import w.fujiko.util.common.csvfileuploadextractor.LengthChecker;
import w.fujiko.util.common.csvfileuploadextractor.NumberChecker;
import w.fujiko.util.common.csvfileuploadextractor.NumberRangeChecker;
import w.fujiko.util.common.csvfileuploadextractor.RequiredChecker;

public class ProductMasterConstants {
	
	public static final String MSG_CONFLICT = "{ \"status\": 409, \"message\": \"この規格・型番は既に存在します\" }";
	public static final String MSG_500 = "{ \"status\": 500, \"message\": \"サーバーエラー\" }";
	
	public static final String MAKER_NOT_EXISTING = "メーカーコード [%s] は存在しません";
	public static final String NOT_EXISTING = "コード [%s] は存在しません";
	
	public static final String INSERT = "insert";
	public static final String UPDATE = "update";
	public static final String INSERTED_ROWS = "新規行数： %d";
	public static final String UPDATED_ROWS = "更新行数： %d";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	// CSV Headers
	public static final String MAKER_CODE = "メーカCD";
	public static final String MODEL_NUMBER = "規格・型番";
	public static final String PRODUCT_NAME = "商品名";
	public static final String KANA = "カナ";
	public static final String INSTRUMENT_BODY = "器具本体";
	public static final String PRODUCT_CLASS_CODE = "商品大分類CD";
	public static final String PRODUCT_CLASS_ITEM_CODE = "商品中分類CD";
	public static final String RATE_CLASS_CODE = "掛率分類CD";
	public static final String LAMP_FLAG = "ランプFLG";
	public static final String LAMP_NAME = "ランプ名";
	public static final String BALLAST_FLAG = "安定器FLG";
	public static final String BALLAST_NAME = "安定器名";
	public static final String TRANS_FLAG = "トランスFLG";
	public static final String TRANS_NAME = "トランス名";
	public static final String SPECIAL_ORDER_CLASS = "特注品区分";
	public static final String WARRANTY_OBJ_CLASS = "保証対象区分";
	public static final String COMPLETE_PRODUCT = "一式商品";
	public static final String NOT_SUBJECT_TO_SLIP_PRINTING = "伝票印字対象外";
	public static final String UNIT_CODE = "単位CD";
	public static final String PURCHASING_COST_DATE = "仕入原価＿適用日";
	public static final String PURCHASING_COST_NO_TAX = "仕入原価＿税抜単価";
	public static final String INVENTORY_UNIT_PRICE_DATE = "在庫単価＿適用日";
	public static final String INVENTORY_UNIT_PRICE_NO_TAX = "在庫単価＿税抜単価";
	public static final String LAST_UNIT_COST_PER_UNIT_DATE = "前回在庫単価＿適用日";
	public static final String LAST_UNIT_COST_PER_UNIT_PRICE = "前回在庫単価＿税抜単価";
	public static final String LIST_PRICE_DATE = "定価＿適用日";
	public static final String LIST_PRICE_NO_TAX = "定価＿税抜単価";
	public static final String PRODUCT_CATEGORY1 = "商品区分１";
	public static final String PRODUCT_CATEGORY2 = "商品区分２";
	public static final String PRODUCT_CATEGORY3 = "商品区分３";
	public static final String PRODUCT_CATEGORY4 = "商品区分４";
	public static final String PRODUCT_CATEGORY5 = "商品区分５";
	public static final String NOTES = "メモ";
	
	public static final String PROGRAM_ID = "JMST07";
	
	public static final String GEN_CLASS_CODE_1 = "SHKB1";
	public static final String GEN_CLASS_CODE_2 = "SHKB2";
	public static final String GEN_CLASS_CODE_3 = "SHKB3";
	public static final String GEN_CLASS_CODE_4 = "SHKB4";
	public static final String GEN_CLASS_CODE_5 = "SHKB5";

	public static final String LONG_PAGE_NAME = "CSVエラー";
	
	public static final String[] CSV_ERROR_HEADER = {
		"行",
		"カラム",
		"エラー"
	};

	public static final String[] CSV_ERROR_FIELDS = {
		"row",
		"column",
		"error"
	};

	public static final String[] HEADERS = new String[] {
			MODEL_NUMBER,
			PRODUCT_NAME,
			KANA,
			INSTRUMENT_BODY,
			PRODUCT_CLASS_CODE,
			PRODUCT_CLASS_ITEM_CODE,
			RATE_CLASS_CODE,
			LAMP_FLAG,
			LAMP_NAME,
			BALLAST_FLAG,
			BALLAST_NAME,
			TRANS_FLAG,
			TRANS_NAME,
			SPECIAL_ORDER_CLASS,
			WARRANTY_OBJ_CLASS,
			COMPLETE_PRODUCT,
			NOT_SUBJECT_TO_SLIP_PRINTING,
			UNIT_CODE,
			PURCHASING_COST_DATE,
			PURCHASING_COST_NO_TAX,
			INVENTORY_UNIT_PRICE_DATE,
			INVENTORY_UNIT_PRICE_NO_TAX,
			LAST_UNIT_COST_PER_UNIT_DATE,
			LAST_UNIT_COST_PER_UNIT_PRICE,
			LIST_PRICE_DATE,
			LIST_PRICE_NO_TAX,
			PRODUCT_CATEGORY1,
			PRODUCT_CATEGORY2,
			PRODUCT_CATEGORY3,
			PRODUCT_CATEGORY4,
			PRODUCT_CATEGORY5,
			NOTES
	};
	
	public static final Checker[][] CHECKERS = new Checker[][] {
		new Checker[] { new RequiredChecker(), new DuplicateChecker(), new LengthChecker(80) }, // "規格・型番", // required, length:80
		new Checker[] { new RequiredChecker(), new LengthChecker(40) }, // "商品名", // required, length:40
		new Checker[] { new LengthChecker(20) },		// "カナ", // length:20
		new Checker[] { new LengthChecker(40) },		// "器具本体", // ok
		new Checker[] { new NumberChecker() },			// "商品大分類CD",
		new Checker[] { new NumberChecker() },			// "商品中分類CD",
		new Checker[] { new NumberChecker() },			// "掛率分類CD",
		new Checker[] { new NumberRangeChecker(0, 2) }, // "ランプFLG", // values: 0,1,2
		new Checker[] {  },								// "ランプ名",
		new Checker[] { new NumberRangeChecker(0, 2) }, // "安定器FLG", // values: 0,1,2
		new Checker[] {  },								// "安定器名",
		new Checker[] { new NumberRangeChecker(0, 2) }, // "トランスFLG", // values: 0,1,2
		new Checker[] {  },								// "トランス名",
		new Checker[] { new BooleanChecker() },			// "特注品区分", // values: 1 or 0
		new Checker[] { new BooleanChecker() },			// "保証対象区分", // values: 1 or 0
		new Checker[] { new BooleanChecker() },			// "一式商品", // values: 1 or 0
		new Checker[] { new BooleanChecker() },			// "伝票印字対象外", // values: 1 or 0
		new Checker[] { new NumberChecker() },		// "単位CD"
		new Checker[] { new DateChecker(DATE_FORMAT) }, // "仕入原価＿適用日", // YYYY/MM/DD
		new Checker[] { new DecimalChecker() },			// "仕入原価＿税抜単価", // decimal
		new Checker[] { new DateChecker(DATE_FORMAT) }, // "在庫単価＿適用日", // YYYY/MM/DD
		new Checker[] { new DecimalChecker() },			// "在庫単価＿税抜単価", // decimal
		new Checker[] { new DateChecker(DATE_FORMAT) }, // "前回在庫単価＿適用日", // YYYY/MM/DD
		new Checker[] { new DecimalChecker() },			// "前回在庫単価＿税抜単価", //decimal
		new Checker[] { new DateChecker(DATE_FORMAT) }, // "定価＿適用日", // YYYY/MM/DD
		new Checker[] { new DecimalChecker() },			// "定価＿税抜単価", // decimal
		new Checker[] { new NumberChecker() },		// "商品区分１",
		new Checker[] { new NumberChecker() },		// "商品区分２",
		new Checker[] { new NumberChecker() },		// "商品区分３",
		new Checker[] { new NumberChecker() },		// "商品区分４",
		new Checker[] { new NumberChecker() },		// "商品区分５",
		new Checker[] {  },								// "メモ"
	};

}
