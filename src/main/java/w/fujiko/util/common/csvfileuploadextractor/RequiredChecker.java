package w.fujiko.util.common.csvfileuploadextractor;

public class RequiredChecker implements Checker {
	
	public static final String ERROR_MESSAGE = "値の入力が必要です";

	@Override
	public CheckResult validate(String value) {
		var checkResult = new DefaultCheckResult();
		String errorMessage = ERROR_MESSAGE;
		
		if(isValid(value)) return checkResult;

		checkResult.setIsError(true);
		checkResult.setErrorMessage(errorMessage);
		
		return checkResult;
	}

	private boolean isValid(String value) {
		if(value == null) {
			return false;
		} else {
			return value.length() > 0;
		}
	}

}