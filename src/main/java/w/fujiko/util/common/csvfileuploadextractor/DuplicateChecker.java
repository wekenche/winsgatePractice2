package w.fujiko.util.common.csvfileuploadextractor;

public class DuplicateChecker implements Checker {
	
	@Override
	public CheckResult validate(String value) {
		return new DefaultCheckResult();
	}

}