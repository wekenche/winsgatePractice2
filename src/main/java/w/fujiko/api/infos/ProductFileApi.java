package w.fujiko.api.infos;

import static w.fujiko.util.common.constants.ProductFileConstants.DRAWING_SPEC_FILE;
import static w.fujiko.util.common.constants.ProductFileConstants.FILETYPE_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.ProductFileConstants.INSTRUCTION_MANUAL_FILE;
import static w.fujiko.util.common.constants.ProductFileConstants.MSG_500;
import static w.fujiko.util.common.constants.ProductFileConstants.PRODUCTID_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.ProductFileConstants.RECORD_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.ProductFileConstants.SMALL_SET_FILE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;

import fte.api.Api;
import fte.api.Created;
import fte.api.Page;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.infos.products.ProductFileDTO;
import w.fujiko.exceptions.FileTypeNotRecognizeException;
import w.fujiko.model.infos.ProductFile;
import w.fujiko.model.masters.products.Product;
import w.fujiko.service.ProductFileHandlerService;
import w.fujiko.service.infos.ProductFileService;
import w.fujiko.service.products.ProductService;
import w.fujiko.util.common.filehandler.ProductFileHandler;
import w.fujiko.util.common.filetypes.determiner.DefaultFileTypeDeterminer;
import w.fujiko.util.common.filetypes.productfiles.DrawingSpecificationFile;
import w.fujiko.util.common.filetypes.productfiles.InstructionManualFile;
import w.fujiko.util.common.filetypes.productfiles.SmallSetFile;
import w.fujiko.util.common.sourcepath.ProductFilesDirectorySourcePath;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.filetype.FujikoFile;

@RestController
@MultipartConfig 
@RequestMapping("/api/product/file")
public class ProductFileApi
	extends Api<ProductFileService,ProductFile,Product> {

	private ModelMapper modelMapper;
	private ProductFileHandler fileHandler;

	@Autowired private ProductFileHandlerService productFileHandlerService;
	@Autowired private ProductService productService;
	private DefaultFileTypeDeterminer fileTypeDeterminer;

	public ProductFileApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
				   .setFieldMatchingEnabled(true)
				   .setFieldAccessLevel(AccessLevel.PRIVATE)
				   .setAmbiguityIgnored(true);

		this.fileHandler = new ProductFileHandler();

		
		fileTypeDeterminer = new DefaultFileTypeDeterminer();
		HashMap<String,FujikoFile> fileTypeHashMap = new HashMap<>();
		fileTypeHashMap.put("smallSet", new SmallSetFile());
		fileTypeHashMap.put("drawingSpecification", new DrawingSpecificationFile());
		fileTypeHashMap.put("instructionManual", new InstructionManualFile());
		fileTypeDeterminer.setfileTypeDictionary(fileTypeHashMap);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<ProductFileDTO> getDTO() {
		
		List<ProductFile> productFileModel = service.get();
		
		Type listType = new TypeToken<List<ProductFileDTO>>() {}.getType();

		List<ProductFileDTO> productFileDTO = this.modelMapper
										          .map(productFileModel,listType);

		return productFileDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<ProductFileDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<ProductFile> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<ProductFileDTO> pageDTO = new Page<ProductFileDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<ProductFileDTO>>() {}.getType();

		List<ProductFileDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Product product = new Product();
		product.setId(id);
		Optional<ProductFile> response = service.get(product);
		//returns NOT_FOUND http status if the record with the supplied id doesn't exist
		if(!response.isPresent())
			return ResponseEntity
				   .status(HttpStatus.NOT_FOUND)
				   .body(StringConverter.convertToReadable(RECORD_NOTFOUND_ERROR_MESSAGE));
		
		ProductFile productFile = response.get();

	
		ProductFileDTO productFileDTO = this.modelMapper
										.map(productFile,ProductFileDTO.class);

		return ResponseEntity
				.ok(productFileDTO);
	}

	@PostMapping("/{productId}")
    public @ResponseBody ResponseEntity<?> documentFileUpload(@RequestParam(value="smallSet",required = false) MultipartFile smallSetFile,
                                                              @RequestParam(value="drawingSpecification",required = false) MultipartFile drawingSpecificationFile,
                                                              @RequestParam(value="instructionManual",required = false) MultipartFile instructionManualFile,                                                           
															  @PathVariable("productId") Integer productId) {

        try{            

				Product product = productService.get(productId).orElseThrow();

				ProductFile productFile = service.get(product).orElse(new ProductFile(product));
				String path = ProductFilesDirectorySourcePath
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(product.getId()).trim());
				
				productFile.setProduct(product);
				service.saveOrUpdate(productFile); // save to db before creating files to avoid file creation on error saving or updating

				this.fileHandler.setDirectorySourcePath(new ProductFilesDirectorySourcePath(path));
				
                if (smallSetFile != null && !smallSetFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(smallSetFile.getOriginalFilename()); //get the file extension
					String documentFileName = this.createDocumentFileName(SMALL_SET_FILE,
																		  product);
					documentFileName = documentFileName.concat(".").concat(fileType);		
					String formerDocumentFileName = productFile.getSmallSetFilename();
					this.deleteFile(formerDocumentFileName,this.fileHandler);			
					productFileHandlerService.write(smallSetFile, documentFileName, this.fileHandler);
					productFile.setSmallSetFilename(documentFileName);
				}
				if (drawingSpecificationFile!=null && !drawingSpecificationFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(drawingSpecificationFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(DRAWING_SPEC_FILE,
																		  product);
					documentFileName = documentFileName.concat(".").concat(fileType);
					String formerDocumentFileName = productFile.getDrawingSpecFilename();
					this.deleteFile(formerDocumentFileName,this.fileHandler);
					productFileHandlerService.write(drawingSpecificationFile, documentFileName, this.fileHandler);
					productFile.setDrawingSpecFilename(documentFileName);
				}
				if (instructionManualFile !=null && !instructionManualFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(instructionManualFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(INSTRUCTION_MANUAL_FILE,
																		  product);
					documentFileName = documentFileName.concat(".").concat(fileType);
					String formerDocumentFileName = productFile.getInstructionManualFilename();
					this.deleteFile(formerDocumentFileName,this.fileHandler);
					productFileHandlerService.write(instructionManualFile, documentFileName, this.fileHandler);
					productFile.setInstructionManualFilename(documentFileName);
				}

        		service.saveOrUpdate(productFile);
				String createdLink = "/api/product/file/"+productFile.getProduct().getId();

				return ResponseEntity
					  .created(new URI(createdLink))
					  .body(new Created(createdLink));
         
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
				   .badRequest()
				   .body(StringConverter.convertToReadable(PRODUCTID_NOTFOUND_ERROR_MESSAGE));
        }catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch (IllegalStateException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }	        
	}	

	@PostMapping("/{productId}/{fileType}")
	public @ResponseBody ResponseEntity<?> updateFile(@RequestParam("file") MultipartFile file,
													  @PathVariable("productId") final int productId,
													  @PathVariable("fileType") final String fileType) {

        try{     
				Product product = productService.get(productId).orElseThrow();
				ProductFile productFile = service.get(product).orElseThrow(); //check if customerCompanyFileId exist else throw exception
		
				String path = ProductFilesDirectorySourcePath
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(productFile.getProduct().getId()).trim());
		
				this.fileHandler.setDirectorySourcePath(new ProductFilesDirectorySourcePath(path));
				
                if (!file.isEmpty()) {
					
					String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
					String documentFileName = "";
					FujikoFile typeOfFile = fileTypeDeterminer.determine(fileType);
					String formerDocumentFileName = typeOfFile.<ProductFile>getFileNameModel(productFile);
					this.deleteFile(formerDocumentFileName,this.fileHandler);
					documentFileName = this.createDocumentFileName(typeOfFile.getFileName(),
																   product);
					documentFileName = documentFileName.concat(".").concat(fileExtension); //add file extension to filename.																						  
					productFile = typeOfFile.setFileNameModel(productFile, documentFileName);
				
					productFileHandlerService.write(file, documentFileName, this.fileHandler); //write file using fileHandler
					service.saveOrUpdate(productFile); //persist model
				}


				return ResponseEntity
					  .ok()
					  .body(new Success());
					  
		}catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
				   .badRequest()
				   .body(StringConverter.convertToReadable(PRODUCTID_NOTFOUND_ERROR_MESSAGE));
        }catch(FileTypeNotRecognizeException ex){
			return ResponseEntity
					.badRequest()
					.body(StringConverter.convertToReadable(FILETYPE_NOTFOUND_ERROR_MESSAGE));
		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe){
			List<Response> errorResponse = new ArrayList<>();
			fte.api.Errors err = new fte.api.Errors();
			err.setDefaultMessage(pe.getLocalizedMessage());			
			errorResponse.add(err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}
	}

	@DeleteMapping("/{productId}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable("productId") Integer productId) {

        try{           
			Product product = new Product();
			product.setId(productId); 
            ProductFile productFile = service.get(product).orElseThrow();
			String path = ProductFilesDirectorySourcePath
						  .getBasePath()
						  .concat("/")
						  .concat(String.valueOf(productFile.getProduct().getId()).trim());

			this.fileHandler.setDirectorySourcePath(new ProductFilesDirectorySourcePath(path));

			Boolean successfullyDelete = productFileHandlerService.deleteDirectory(this.fileHandler);

            if(!successfullyDelete){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
			}
			
			service.delete(productFile);
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body(StringConverter.convertToReadable(PRODUCTID_NOTFOUND_ERROR_MESSAGE));
        }
        catch (IllegalStateException  | IOException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }
        return ResponseEntity
                .ok()
                .body(new Success());
	}

	@DeleteMapping("/{productId}/{fileType}")
	public @ResponseBody ResponseEntity<?> delete(@RequestBody JsonNode requestBody,
												  @PathVariable("productId") Integer productId,
												  @PathVariable("fileType") String fileType) {

		String fileName = requestBody.get("filename").asText();

        try{
			Product product = productService.get(productId).orElseThrow();
			       
			ProductFile productFile = service.get(product).orElseThrow();

			FujikoFile typeOfFile = fileTypeDeterminer.determine(fileType);
						
			productFile = typeOfFile.setFileNameModel(productFile, null);

			String path = ProductFilesDirectorySourcePath
						  .getBasePath()
						  .concat("/")
						  .concat(String.valueOf(productFile.getProduct().getId()).trim());

			this.fileHandler.setDirectorySourcePath(new ProductFilesDirectorySourcePath(path));
			
			Boolean successfullyDelete = productFileHandlerService.delete(fileName,this.fileHandler);
            if(!successfullyDelete){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
			}
			service.saveOrUpdate(productFile);
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body(StringConverter.convertToReadable(PRODUCTID_NOTFOUND_ERROR_MESSAGE));
        }catch(FileTypeNotRecognizeException ex){
			return ResponseEntity
					.badRequest()
					.body(StringConverter.convertToReadable(FILETYPE_NOTFOUND_ERROR_MESSAGE));
		}catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }
        catch (IllegalStateException  | IOException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }
        return ResponseEntity
                .ok()
                .body(new Success());
	}
	
	/***
	 * Creates a document filename
	 * @param baseName
	 * @param product
	 * @return filename
	 */
	//sample output: K_DAIKO_KIKAKUKATABAN
	private String createDocumentFileName(String baseName,Product product){
		final String NAME_SEPARATOR="_";
		String makerName = product.getMaker().getName();
		String modelNumber = String.valueOf(product.getModelNumber()).trim();
		return baseName
			  .concat(NAME_SEPARATOR)
			  .concat(makerName)
			  .concat(NAME_SEPARATOR)
			  .concat(modelNumber);
	}
	
	private void deleteFile(String fileName,ProductFileHandler fileHandler){
		try {
			fileHandler.delete(fileName);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
}