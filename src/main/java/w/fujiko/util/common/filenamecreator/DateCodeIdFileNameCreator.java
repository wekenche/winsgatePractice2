package w.fujiko.util.common.filenamecreator;

import java.util.Calendar;
import java.util.Date;

import w.fujiko.util.filenamecreator.FilenameCreator;

public class DateCodeIdFileNameCreator implements FilenameCreator{
    private Date date;
    private String code;
    private Integer id;

    public DateCodeIdFileNameCreator(Date date,String code,Integer id){
        this.date = date;
        this.code = code;
        this.id = id;
    }

    @Override
    public String createName() {
        return this.createDocumentFileName();
    }

    /***
	 * Creates a document filename
	 * @return filename
	 */
	//sample output: 99999_123_201801
	private String createDocumentFileName(){
        final String NAME_SEPARATOR="_";
        String documentYearAndMonth = this.concatenateYearAndMonthAsString(date);
        String idString = String.valueOf(id).trim();

		return code.trim()
			  .concat(NAME_SEPARATOR)
			  .concat(idString)
			  .concat(NAME_SEPARATOR)
			  .concat(documentYearAndMonth);
    }
    
    /**
	 * Concatenates year and month of a date
	 * @param date
	 * @return concatenated Year and Month. e.g. 201810
	 */
	private String concatenateYearAndMonthAsString(Date date){
		String concatenatedYearAndMonth="";
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date);
		concatenatedYearAndMonth = String.valueOf(cal.get(Calendar.YEAR))
										 .concat(String.valueOf(cal.get(Calendar.MONTH) + 1));
										 
		return concatenatedYearAndMonth;
	}

}