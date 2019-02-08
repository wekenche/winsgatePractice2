package w.fujiko.util.common.generator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This class is used when generating PDF
 * 
 * @author festadillo
 *
 */
public class PDFListGenerator {
	
	public static final String FONT_RESOURCE = "fonts/KozMinPro-Regular.otf";	
	public static final String DATE_FORMAT = "yyyy/MM/dd (HH:mm)";
	public static final String SQUARE_SYMBOL = " \u25a0 ";
	public static final String PAGE_LABEL = "PAGE : ";
	public static final String DATE_LABEL = "DATE : ";
	public static final String EMPTY = "";
	
	/**
	 * Generates a PDF formatted data.
	 * 
	 * @param table
	 * @param rangeContent
	 * @param title
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public <E>ByteArrayInputStream generate(PDFListTable<E> table) 
			throws IllegalArgumentException, IllegalAccessException {
		
		PDFHeader event = new PDFHeader(table.getRangeContent(), table.getTitle());
		
		Document document = new Document(PageSize.A4, 36, 36, 50, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		String[] headerList = table.getHeaders();
		String[] fieldList = table.getFields();
		Map<String, String> defaultValues = table.getDefaultFieldValues();
		List<E> models = table.getDataSource();
		Class<E> klazz = table.getKlazz();
		float[] columnWidths = table.getColumnWidths();
		boolean applyBorders = table.isApplyBorders();
		
		try {
			
			PdfPTable pdfTable = new PdfPTable(headerList.length);
			
			if(columnWidths != null && columnWidths.length > 0) {
				if(columnWidths.length == headerList.length) {
					pdfTable.setWidths(columnWidths);
				}
			}
			
			pdfTable.setWidthPercentage(100);
			pdfTable.setHeaderRows(1);
						
			Font font = FontFactory.getFont(FONT_RESOURCE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);
			
			/** Header Creation */
			for(String header : headerList) {
				PdfPCell hcell;
				hcell = new PdfPCell(new Phrase(header, font));
				hcell = formatTableHeaderCell(hcell, applyBorders);
				pdfTable.addCell(hcell);
			}

			/** Content Row Creation */
			for (E model : models) {
				PdfPCell cell;
				
				/** Column Creation */
				for(String fieldName : fieldList) {
					String fieldValue;	
					if(hasDefaultValue(fieldName, defaultValues)) {
						fieldValue = defaultValues.get(fieldName);
					} else {
						fieldValue = getFieldValue(fieldName, klazz, model);
					}
					cell = new PdfPCell(new Phrase(fieldValue, font));
					cell = formatTableBodyCell(cell, applyBorders);
					pdfTable.addCell(cell);
				}
			}
			
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(event);
			
			document.open();
			document.add(new Chunk(EMPTY));
			document.add(pdfTable);
			document.close(); 
			
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	/**
	 * Sets the formatting of the table cell
	 * 
	 * @param cell
	 * @return
	 */
	private PdfPCell formatCell(PdfPCell cell) {
		cell.setPaddingLeft(5);
		cell.setPaddingRight(5);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		return cell;
	}
	
	/**
	 * Sets the formatting of the header cell
	 * 
	 * @param cell
	 * @return
	 */
	private PdfPCell formatTableHeaderCell(PdfPCell cell, boolean applyBorders) {
		cell.setPaddingBottom(5);
		if(!applyBorders) {
			// apply bottom border if value is false.
			cell.setBorder(Rectangle.BOTTOM);
		}
		return formatCell(cell);
	}
	
	/**
	 * Sets the formatting of the body cell
	 * 
	 * @param cell
	 * @return
	 */
	private PdfPCell formatTableBodyCell(PdfPCell cell, boolean applyBorders) {
		if(!applyBorders) {
			cell.setBorder(Rectangle.NO_BORDER);
		}
		return formatCell(cell);
	}
	
	/**
	 * Gets the value of the object's instance variable based on the
	 * specified field name.
	 * 
	 * @param fieldName
	 * @param klazz
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private <E>String getFieldValue(String fieldName, Class<?> klazz, E obj) 
			throws IllegalArgumentException, IllegalAccessException {
		
		int index = getFieldIndex(fieldName, klazz);
		Field[] f = klazz.getDeclaredFields();
		f[index].setAccessible(true);
		String result = f[index].get(obj).toString();
		
		return result;
	}
		
	/**
	 * Gets the field's index number inside a class based on the
	 * specified field name.
	 * 
	 * @param fieldName
	 * @param klazz
	 * @return
	 */
	private int getFieldIndex(String fieldName, Class<?> klazz) {
		Field[] f = klazz.getDeclaredFields();
		return IntStream.range(0, f.length)
				.filter(i -> fieldName.equals(f[i].getName()))
				.findFirst()
				.orElse(-1);
	}
	
	/**
	 * Determines if the specified field name has a default value.
	 * 
	 * @param fieldName
	 * @param defaultValues
	 * @return
	 */
	private boolean hasDefaultValue(String fieldName, Map<String, String> defaultValues) {
		if(defaultValues.size() > 0) {
			if(defaultValues.containsKey(fieldName)) {
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * The purpose of this inner class is for the generation of
	 * header in every page of the PDF document.
	 * 
	 */
	public class PDFHeader extends PdfPageEventHelper {
		
		private PdfPTable table;
		private Font font;
		private Font titleFont;
		private String rangeContent;
		private String title;
		private String dateTime;
		private float tableHeight;

		public PDFHeader(String rangeContent, String title) {
			this.rangeContent = rangeContent;
			this.title = title;
			this.font = FontFactory.getFont(FONT_RESOURCE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8);
			this.titleFont = FontFactory.getFont(FONT_RESOURCE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 9);
			this.dateTime = new SimpleDateFormat(DATE_FORMAT)
					.format(Calendar.getInstance().getTime());
		}
 
		/**
		 * 
		 * Invoked every page for the creation of the page header.
		 * 
		 */
		public void onEndPage(PdfWriter writer, Document document) {
			table = new PdfPTable(4);
			table.setTotalWidth(523);
			table.setLockedWidth(true);
			
			try {
				table.setWidths(new float[] { 10, 20, 9, 4 });
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			PdfPCell firstColumnCell;
			firstColumnCell = new PdfPCell(new Phrase(this.rangeContent, font));
			firstColumnCell.setBorder(Rectangle.NO_BORDER);
			firstColumnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			firstColumnCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(firstColumnCell);

			PdfPCell titleCell;
			String titleContent = SQUARE_SYMBOL + this.title + SQUARE_SYMBOL;
			titleCell = new PdfPCell(new Phrase(titleContent, titleFont));
			titleCell.setBorder(Rectangle.NO_BORDER);
			titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(titleCell);

			String dateContent = DATE_LABEL + this.dateTime;
			
			PdfPCell dateCell;
			dateCell = new PdfPCell(new Phrase(dateContent, font));
			dateCell.setBorder(Rectangle.NO_BORDER);
			dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(dateCell);
			
			PdfPCell pageCell;
			pageCell = new PdfPCell(new Phrase(PAGE_LABEL + writer.getPageNumber(), font));
			pageCell.setBorder(Rectangle.NO_BORDER);
			pageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(pageCell);

			tableHeight = table.getTotalHeight();

			table.writeSelectedRows(0, -1,
					document.left(),
					document.top() + ((document.topMargin() + tableHeight) / 2),
					writer.getDirectContent());
		}

	}

}