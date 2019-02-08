package w.fujiko.util.common.generator.jasperpdf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportDataManager {
	
	public void generatePDF(String jrxmlPath, Map<String, Object> parameterMap, 
			HttpServletResponse response) {
		
		generatePDF(jrxmlPath, parameterMap, new JREmptyDataSource(), response);
	}
	
	public void generatePDF(String jrxmlPath, Map<String, Object> parameterMap, 
			JRDataSource dataSource, HttpServletResponse response) {
		
		InputStream stream  = getClass().getResourceAsStream(jrxmlPath);
		
		try {
			JasperReport jasperReport  = JasperCompileManager.compileReport(stream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, dataSource);

			response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}

}