package w.fujiko.util.common.csvfileuploadextractor;

public class NumberRangeChecker extends NumberChecker implements Checker {
	
	public static int from;
	public static int to;
	public static final String ERROR_MESSAGE = "値は%dから%dの間にする必要があります";
	
	public NumberRangeChecker(int from, int to) {
		NumberRangeChecker.from = from;
		NumberRangeChecker.to = to;
	}

	@Override
	public CheckResult validate(String value) {		
		var checkResult = new DefaultCheckResult();
		if(value == null || value.length() == 0){
			return checkResult;
		} else {
			if(super.validate(value).hasError()) {
				checkResult.setIsError(true);
				checkResult.setErrorMessage(NumberChecker.ERROR_MESSAGE);
				return checkResult;

			} else if (!isValid(value)) {
				String errorMessage = String.format(ERROR_MESSAGE, from, to);
				checkResult.setIsError(true);
				checkResult.setErrorMessage(errorMessage);
				return checkResult;
			} else {
				return checkResult;
			}
		}
	}

	private boolean isValid(String value) {
		boolean result = false;
		int number = Integer.parseInt(value);
		if(number >= from && number <= to ) {
			result = true;
		}
		return result;
	}

}