package w.fujiko.controller;

import java.io.IOException;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import w.fujiko.service.CustomerCompanyFileHandlerService;
import w.fujiko.service.infos.CustomerCompanyFileService;
import w.fujiko.util.common.filehandler.CustomerCompanyDocsFileHandler;
import w.fujiko.util.common.sourcepath.CustomerCompanyDocsDirectorySourcePath;
import w.fujiko.model.infos.CustomerCompanyFile;


@Controller
public class CustomerCompanyFileController {
    
    @Autowired CustomerCompanyFileHandlerService customerCompanyFileHandlerService;
    @Autowired CustomerCompanyFileService customerCompanyFileService;
	private CustomerCompanyDocsFileHandler fileHandler;

	public CustomerCompanyFileController(){
        this.fileHandler = new CustomerCompanyDocsFileHandler();
	}
	
	@GetMapping("/documents/customer/company/{customerCompanyFileId}/{filename}/")
	public void getImageAsByteArray(@PathVariable(value="customerCompanyFileId") final int customerCompanyFileId,
									@PathVariable(value="filename") final String filename,
									HttpServletResponse response) throws IOException {

		CustomerCompanyFile customerCompanyFile = customerCompanyFileService.get(customerCompanyFileId).orElseThrow();

		String path = CustomerCompanyDocsDirectorySourcePath
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(customerCompanyFile.getId()).trim())
							  .concat("/")
							  .concat(this.stringifyDate(customerCompanyFile.getDate()));

	    this.fileHandler.setDirectorySourcePath(new CustomerCompanyDocsDirectorySourcePath(path));

	    response.setContentType(MediaType.ALL_VALUE);
	    var fileInput = customerCompanyFileHandlerService.get(filename,fileHandler);
	    IOUtils.copy(fileInput,response.getOutputStream());
	}

	private String stringifyDate(Date date){
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");		
		return dateFormatter.format(date);
	}
	
}
