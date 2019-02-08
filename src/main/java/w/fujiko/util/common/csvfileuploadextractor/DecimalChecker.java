package w.fujiko.util.common.csvfileuploadextractor;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class DecimalChecker implements Checker {
	
	public static final String ERROR_MESSAGE = "値は有効な10進数にする必要があります";
	
	@Override
	public CheckResult validate(String value) {
		String errorMessage = ERROR_MESSAGE;
		var checkResult = new DefaultCheckResult();
		if(value == null || value.length() == 0){
			return checkResult;
		} else {
			if(isValid(value)) return checkResult;

			checkResult.setIsError(true);
			checkResult.setErrorMessage(errorMessage);
			
			return checkResult;

		}
	}

	private boolean isValid(String value) {
		boolean result = false;
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(value, pos);
		result = value.length() == pos.getIndex();
		return result; 
	}

}