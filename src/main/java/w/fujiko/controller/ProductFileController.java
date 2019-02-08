package w.fujiko.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import w.fujiko.service.ProductFileHandlerService;
import w.fujiko.service.infos.ProductFileService;
import w.fujiko.service.products.ProductService;
import w.fujiko.util.common.filehandler.ProductFileHandler;
import w.fujiko.util.common.sourcepath.ProductFilesDirectorySourcePath;
import w.fujiko.model.infos.ProductFile;
import w.fujiko.model.masters.products.Product;


@Controller
public class ProductFileController {
    
    @Autowired ProductFileHandlerService productFileHandlerService;
	@Autowired ProductFileService productFileService;
	@Autowired ProductService productService;

	private ProductFileHandler fileHandler;

	public ProductFileController(){
        this.fileHandler = new ProductFileHandler();
	}
	
	@GetMapping("/documents/product/{productId}/{filename}")
	public void getImageAsByteArray(@PathVariable(value="productId") final int productId,
									@PathVariable(value="filename") final String filename,
									HttpServletResponse response) throws IOException {
		
		Product product = new Product();
		product.setId(productId);
		ProductFile productFile = productFileService.get(product).orElseThrow();

		String path = ProductFilesDirectorySourcePath
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(productFile.getProduct().getId()).trim());

	    this.fileHandler.setDirectorySourcePath(new ProductFilesDirectorySourcePath(path));

	    response.setContentType(MediaType.ALL_VALUE);
	    var fileInput = productFileHandlerService.get(filename,fileHandler);
	    IOUtils.copy(fileInput,response.getOutputStream());
	}

	@GetMapping("/file/product/error/csv")
	public void getProductCaptureCSVErrorFile(@RequestParam("filename")final String filename,
											  HttpServletResponse response) throws Exception {

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition","filename="+filename);
	    var fileInput = productService.getCSVErrorFile(filename);
		IOUtils.copy(fileInput,response.getOutputStream());
		response.flushBuffer();
	}
}

