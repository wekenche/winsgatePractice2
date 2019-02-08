package w.fujiko.util.common.csvfileuploadextractor;

public class CSVCellInfo {
	
	private int rowNumber;
	
	private String header;
	
	private String value;

	public CSVCellInfo(int rowNumber, String header, String value) {
		super();
		this.rowNumber = rowNumber;
		this.header = header;
		this.value = value;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}