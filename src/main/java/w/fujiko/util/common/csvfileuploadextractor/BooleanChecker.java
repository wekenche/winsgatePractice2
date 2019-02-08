package w.fujiko.util.common.csvfileuploadextractor;

public class BooleanChecker implements Checker {
	
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	public static final String ERROR_MESSAGE = "値は %sまたは%sにする必要があります";
	

	@Override
	public CheckResult validate(String value) {
		String errorMessage = String.format(ERROR_MESSAGE, TRUE, FALSE);
		var checkResult = new DefaultCheckResult();
		if(value == null || value.length() == 0){
			return checkResult;
		} else {
			char c = value.charAt(0);
			if(Character.isDigit(c)) {
				int num = Character.getNumericValue(c);
				
				if(isValid(Integer.toString(num))) return checkResult;

				checkResult.setErrorMessage(errorMessage);
				checkResult.setIsError(true);				
			} else {
				checkResult.setErrorMessage(errorMessage);
				checkResult.setIsError(true);
			}
		}
		return checkResult;
	}

	private boolean isValid(String value) {
		boolean result = false;
		if(value.equals(TRUE) || value.equals(FALSE)) {
			result = true;
		}
		return result;
	}

}