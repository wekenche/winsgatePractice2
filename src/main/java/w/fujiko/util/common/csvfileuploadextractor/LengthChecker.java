package w.fujiko.util.common.csvfileuploadextractor;

public class LengthChecker implements Checker {
	
	public static final String ERROR_MESSAGE = "最大%s文字までにする必要があります";
	
	private int length;
	
	public LengthChecker(int length) {
		this.length = length;
	}

	@Override
	public CheckResult validate(String value) {
		String errorMessage = String.format(ERROR_MESSAGE, length);
		var checkResult = new DefaultCheckResult();
		if(isValid(value)) return checkResult;

		checkResult.setIsError(true);
		checkResult.setErrorMessage(errorMessage);
		return checkResult;
	}

	private boolean isValid(String value) {
		boolean result = false;
		if(value == null) {
			result = true;
		} else {
			if(value.length() <= this.length) {
				result = true;
			}
		}
		return result;
	}

}