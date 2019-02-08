package w.fujiko.model.masters.products;

public class ProductCSVExtractionErrorModel {
	
	private int row;
	private String column;
	private String error;
	
	public ProductCSVExtractionErrorModel(int row, String column, String error) {
		super();
		this.row = row;
		this.column = column;
		this.error = error;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}