package w.fujiko.util.common.generator;

/**
 * This class is used as a data by 
 * PDFListGenerator in order to generate PDF.
 * 
 * @author festadillo
 *
 * @param <E>
 */
public class PDFListTable<E> extends Table<E> {

	/**
	 * REQUIRED
	 * 
	 * The value of this property will be shown at the
	 * leftmost part of the header in each page.
	 * 
	 */
	private String rangeContent;
	
	
	/**
	 * REQUIRED
	 * 
	 * This will serve as the title of the table in the generated PDF.
	 * It is shown at the center of the header in each page.
	 * 
	 */
	private String title;
	
	
	/**
	 * OPTIONAL 
	 * 
	 * Sets the widths of the columns in relation to one another.
	 * 
	 * For example:
	 * 
	 * float[] columnWidths = {1, 2, 3};
	 * 
	 * The first column has the smallest width.
	 * The second column's width is twice greater than that of the first one.
	 * The last column has the greatest width.
	 * It is 3x greater than the first column.
	 * 
	 * Note: The length of the columnWidths must match the number
	 * of table columns (Table.headers.length) in order to take effect.
	 * 
	 */
	private float[] columnWidths;
	

	/**
	 * OPTIONAL
	 * 
	 * true = applies border to table
	 * false = doesn't apply border to table
	 */
	private boolean applyBorders = false;


	public String getRangeContent() {
		return rangeContent;
	}


	public void setRangeContent(String rangeContent) {
		this.rangeContent = rangeContent;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public float[] getColumnWidths() {
		return columnWidths;
	}


	public void setColumnWidths(float[] columnWidths) {
		this.columnWidths = columnWidths;
	}


	public boolean isApplyBorders() {
		return applyBorders;
	}


	public void setApplyBorders(boolean applyBorders) {
		this.applyBorders = applyBorders;
	}

}