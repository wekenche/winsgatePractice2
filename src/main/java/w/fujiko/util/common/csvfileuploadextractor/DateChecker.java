package w.fujiko.util.common.csvfileuploadextractor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateChecker implements Checker {
	
	public static final String ERROR_MESSAGE = "値は %s の形式にする必要があります";
	
	private String dateFormat;
	
	
	public DateChecker(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public CheckResult validate(String value) {
		String errorMessage = String.format(ERROR_MESSAGE, dateFormat.toUpperCase());
		var checkResult = new DefaultCheckResult();
		if(value == null || value.length() == 0){
			return checkResult;
		} else {
			value = convertToNormalNumber(value);
			if(isValid(value)) return checkResult;
			
			checkResult.setErrorMessage(errorMessage);
			checkResult.setIsError(true);
			
			return checkResult;			
		}
	}

	private boolean isValid(String value) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(dateFormat);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					e2.printStackTrace();
				}
			}
		}

		return false;
	}
	
	/**
	 * This is just a workaround
	 * 
	 * It transforms special number to normal number.
	 * Example: 
	 * 	From: ２０１０ 	To: 2010
	 * 
	 * @param str
	 * @return
	 */
	private String convertToNormalNumber(String str) {
		String result = "";
		int size = str.length();
		for(int i = 0; i < size; i++) {
			char c = str.charAt(i);
			if(Character.isDigit(c)) {
				int num = Character.getNumericValue(c);
				result += num;
			} else {
				result += c;
			}
		}
		return result;
	}

}