package w.fujiko.api.infos;

import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.ADDRESS_CHANGE_DOCUMENT;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.COMPANY_SURVEY_DOCUMENT;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.DOCUMENT_DATE_DUPLICATE_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.DOCUMENT_DATE_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.FILETYPE_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.GROUPID_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.MSG_500;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.OLD_SALES_MANAGEMENT_DOCUMENT;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.OTHER_DOCUMENT;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.RECORD_NOTFOUND_ERROR_MESSAGE;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.TRANSACTION_APPROVAL_DOCUMENT;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.TRANSACTION_REGISTER_DOCUMENT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import fte.api.FileCreated;
import fte.api.Page;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.infos.CustomerCompanyFileCreateDTO;
import w.fujiko.dto.infos.CustomerCompanyFileDTO;
import w.fujiko.dto.infos.CustomerCompanyFileUpdateDTO;
import w.fujiko.exceptions.FileTypeNotRecognizeException;
import w.fujiko.model.infos.CustomerCompanyFile;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.CustomerCompanyFileHandlerService;
import w.fujiko.service.customers.CustomerGroupService;
import w.fujiko.service.infos.CustomerCompanyFileService;
import w.fujiko.util.common.filehandler.CustomerCompanyDocsFileHandler;
import w.fujiko.util.common.filenamecreator.DateCodeIdFileNameCreator;
import w.fujiko.util.common.filetypes.customercompanyfiles.AddressChangeFile;
import w.fujiko.util.common.filetypes.customercompanyfiles.CompanySurveyFile;
import w.fujiko.util.common.filetypes.customercompanyfiles.OldSalesManagementFile;
import w.fujiko.util.common.filetypes.customercompanyfiles.OthersFile;
import w.fujiko.util.common.filetypes.customercompanyfiles.TransactionApprovalFile;
import w.fujiko.util.common.filetypes.customercompanyfiles.TransactionRegisterFile;
import w.fujiko.util.common.filetypes.determiner.DefaultFileTypeDeterminer;
import w.fujiko.util.common.sourcepath.CustomerCompanyDocsDirectorySourcePath;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.filetype.FujikoFile;;

@RestController
@MultipartConfig 
@RequestMapping("/api/customer/company/file")
public class CustomerCompanyFileApi
	extends Api<CustomerCompanyFileService,CustomerCompanyFile,Integer> {

	private ModelMapper modelMapper;
	private CustomerCompanyDocsFileHandler fileHandler;

	@Autowired private CustomerGroupService customerGroupService;
	@Autowired private CustomerCompanyFileHandlerService customerComapanyFileHandlerService;
	private DefaultFileTypeDeterminer fileTypeDeterminer;

	public CustomerCompanyFileApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
				   .setFieldMatchingEnabled(true)
				   .setFieldAccessLevel(AccessLevel.PRIVATE)
				   .setAmbiguityIgnored(true);

		this.fileHandler = new CustomerCompanyDocsFileHandler();

		fileTypeDeterminer = new DefaultFileTypeDeterminer();
		HashMap<String,FujikoFile> fileTypeHashMap = new HashMap<>();
		fileTypeHashMap.put("transactionApproval", new TransactionApprovalFile());
		fileTypeHashMap.put("companySurvey", new CompanySurveyFile());
		fileTypeHashMap.put("transactionRegister", new TransactionRegisterFile());
		fileTypeHashMap.put("addressChange", new AddressChangeFile());
		fileTypeHashMap.put("others", new OthersFile());
		fileTypeHashMap.put("oldSalesManagement", new OldSalesManagementFile());
		fileTypeDeterminer.setfileTypeDictionary(fileTypeHashMap);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<CustomerCompanyFileDTO> getDTO() {
		
		List<CustomerCompanyFile> customerCompanyFileModel = service.get();
		
		Type listType = new TypeToken<List<CustomerCompanyFileDTO>>() {}.getType();

		List<CustomerCompanyFileDTO> customerCompanyFileDTO = this.modelMapper
										  .map(customerCompanyFileModel,listType);

		return customerCompanyFileDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<CustomerCompanyFileDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<CustomerCompanyFile> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<CustomerCompanyFileDTO> pageDTO = new Page<CustomerCompanyFileDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<CustomerCompanyFileDTO>>() {}.getType();

		List<CustomerCompanyFileDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<CustomerCompanyFile> response = service.get(id);
		//returns NOT_FOUND http status if the record with the supplied id doesn't exist
		if(!response.isPresent())
			return ResponseEntity
				   .status(HttpStatus.NOT_FOUND)
				   .body(StringConverter.convertToReadable(RECORD_NOTFOUND_ERROR_MESSAGE));
		
		CustomerCompanyFile customerCompanyFile = response.get();

	
		CustomerCompanyFileDTO customerCompanyFileDTO = this.modelMapper
										.map(customerCompanyFile,CustomerCompanyFileDTO.class);

		return ResponseEntity
				.ok(customerCompanyFileDTO);
	}

	@GetMapping("/customergroup/{customerGroupId}")
	public @ResponseBody List<CustomerCompanyFileDTO> getByCustomerGroupId(@PathVariable(value="customerGroupId") final Integer customerGroupId) {
		List<CustomerCompanyFile> customerCompanyFileList = service.findByCustomerGroupId(customerGroupId);
		Type listType = new TypeToken<List<CustomerCompanyFileDTO>>() {}.getType();

		List<CustomerCompanyFileDTO> customerCompanyFileDTO = this.modelMapper
										  .map(customerCompanyFileList,listType);

		return customerCompanyFileDTO;
	}

	@PostMapping("/create/{customerGroupId}/{documentDate}/{createdBy}/{createdAt}")
    public @ResponseBody ResponseEntity<?> documentFileUpload(@RequestParam(value="transactionApproval",required = false) MultipartFile transactionApprovalFile,
                                                              @RequestParam(value="companySurvey",required = false) MultipartFile companySurveyFile,
                                                              @RequestParam(value="transactionRegister",required = false) MultipartFile transactionRegisterFile,
                                                              @RequestParam(value="addressChange",required = false) MultipartFile addressChangeFile,
                                                              @RequestParam(value="others",required = false) MultipartFile othersFile,
                                                              @RequestParam(value="oldSalesManagement",required = false) MultipartFile oldSalesManagementFile,
															  @PathVariable("customerGroupId") Integer customerGroupId,
															  @PathVariable("documentDate") String documentDateString,
															  @PathVariable("createdBy") int createdById,
															  @PathVariable("createdAt") String createdAtId) {

		Date documentDate;

        try{            
				documentDate = new SimpleDateFormat("yyyy-MM-dd").parse(documentDateString);
				CustomerGroup customerGroup = customerGroupService.get(customerGroupId).orElseThrow();

				if(!service.isUniqueDocumentDate(documentDate,customerGroup.getId()))
					return this.throwDocumentDateDuplicateError();

				CustomerCompanyFile customerCompanyFile = new CustomerCompanyFile();
				String path = CustomerCompanyDocsDirectorySourcePath
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(customerGroup.getId()).trim())
							  .concat("/")
							  .concat(this.stringifyDate(documentDate));

				customerCompanyFile.setCustomerGroup(customerGroup);
				customerCompanyFile.setDate(documentDate);
				User creator = new User();
				creator.setId(createdById);
				Program createdAt = new Program();
				createdAt.setId(createdAtId);
				customerCompanyFile.setCreatedBy(creator);
				customerCompanyFile.setCreatedAt(createdAt);
				service.saveOrUpdate(customerCompanyFile);

				this.fileHandler.setDirectorySourcePath(new CustomerCompanyDocsDirectorySourcePath(path));
				
                if (transactionApprovalFile != null && !transactionApprovalFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(transactionApprovalFile.getOriginalFilename()); //get the file extension
					String documentFileName = this.createDocumentFileName(TRANSACTION_APPROVAL_DOCUMENT,
												                          documentDate,
																		  customerGroup);
					documentFileName = documentFileName.concat(".").concat(fileType);					
					customerComapanyFileHandlerService.write(transactionApprovalFile, documentFileName, this.fileHandler);
					customerCompanyFile.setTransactionApprovalFilename(documentFileName);
				}
				if (companySurveyFile!=null && !companySurveyFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(companySurveyFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(COMPANY_SURVEY_DOCUMENT,
												                          documentDate,
																		  customerGroup);
					documentFileName = documentFileName.concat(".").concat(fileType);
					customerComapanyFileHandlerService.write(companySurveyFile, documentFileName, this.fileHandler);
					customerCompanyFile.setCompanySurveyFilename(documentFileName);
				}
				if (transactionRegisterFile !=null && !transactionRegisterFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(transactionRegisterFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(TRANSACTION_REGISTER_DOCUMENT,
												                          documentDate,
																		  customerGroup);
					documentFileName = documentFileName.concat(".").concat(fileType);
					customerComapanyFileHandlerService.write(transactionRegisterFile, documentFileName, this.fileHandler);
					customerCompanyFile.setTransactionRegisterFilename(documentFileName);
				}
				if (addressChangeFile!=null && !addressChangeFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(addressChangeFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(ADDRESS_CHANGE_DOCUMENT,
												                          documentDate,
																		  customerGroup);
					documentFileName = documentFileName.concat(".").concat(fileType);
					customerComapanyFileHandlerService.write(addressChangeFile, documentFileName, this.fileHandler);
					customerCompanyFile.setAddressChangeFilename(documentFileName);
				}
				if (othersFile!=null && !othersFile.isEmpty()) {
					String fileType = FilenameUtils.getExtension(othersFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(OTHER_DOCUMENT,
												                          documentDate,
																		  customerGroup);
					documentFileName = documentFileName.concat(".").concat(fileType);
					customerComapanyFileHandlerService.write(othersFile, documentFileName, this.fileHandler);
					customerCompanyFile.setOthersDocFilename(documentFileName);
				}
				if (oldSalesManagementFile!=null && !oldSalesManagementFile.isEmpty()) {
              		String fileType = FilenameUtils.getExtension(oldSalesManagementFile.getOriginalFilename());
					String documentFileName = this.createDocumentFileName(OLD_SALES_MANAGEMENT_DOCUMENT,
												                          documentDate,
																		  customerGroup);
					documentFileName = documentFileName.concat(".").concat(fileType);
					customerComapanyFileHandlerService.write(oldSalesManagementFile, documentFileName, this.fileHandler);
					customerCompanyFile.setOldSalesManagementDocFilename(documentFileName);
        		}

        		service.saveOrUpdate(customerCompanyFile);
				String createdLink = "/api/customer/company/file/"+customerCompanyFile.getId();

				return ResponseEntity
					  .created(new URI(createdLink))
					  .body(new Created(createdLink));
         
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
				   .badRequest()
				   .body(StringConverter.convertToReadable(GROUPID_NOTFOUND_ERROR_MESSAGE));
        }
        catch (IllegalStateException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }catch (ParseException e) {		
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StringConverter.convertToReadable(MSG_500));
		}catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StringConverter.convertToReadable(MSG_500));
        }	        
	}

	@PostMapping("/update/{customerCompanyFileId}/{fileType}/{updatedBy}/{updatedAt}")
	public @ResponseBody ResponseEntity<?> updateFile(@RequestParam("file") MultipartFile file,
													  @PathVariable("customerCompanyFileId") final int customerCompanyFileId,
													  @PathVariable("fileType") final String fileType,													  
													  @PathVariable("updatedBy") final int updatedById,
													  @PathVariable("updatedAt") final String updatedAtId) {

		Date documentDate;

        try{            
				CustomerCompanyFile customerCompanyFile = service.get(customerCompanyFileId).orElseThrow(); //check if customerCompanyFileId exist else throw exception
				
				documentDate = customerCompanyFile.getDate();

				CustomerGroup customerGroup = customerCompanyFile.getCustomerGroup();
			
				String path = CustomerCompanyDocsDirectorySourcePath //create destination path: '/home/storage/documents/customer/company/{id}/{date}'
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(customerGroup.getId()).trim())
							  .concat("/")
							  .concat(this.stringifyDate(documentDate));

				User updator = new User();
				updator.setId(updatedById);
				Program updatedAt = new Program();
				updatedAt.setId(updatedAtId);
				customerCompanyFile.setUpdatedBy(updator);
				customerCompanyFile.setUpdatedAt(updatedAt);

				this.fileHandler.setDirectorySourcePath(new CustomerCompanyDocsDirectorySourcePath(path));
				String documentFileName = "";
                if (!file.isEmpty()) {
					
					String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
					
					FujikoFile typeOfFile = fileTypeDeterminer.determine(fileType);
					String formerDocumentFileName = typeOfFile.<CustomerCompanyFile>getFileNameModel(customerCompanyFile);
					this.deleteFile(formerDocumentFileName,this.fileHandler);
					documentFileName = typeOfFile.getFileName(new DateCodeIdFileNameCreator(documentDate,
																						  customerGroup.getCode(),
																						  customerGroup.getId()));
					documentFileName = documentFileName.concat(".").concat(fileExtension); //add file extension to filename.																						  
					customerCompanyFile = typeOfFile.setFileNameModel(customerCompanyFile, documentFileName);
				
					customerComapanyFileHandlerService.write(file, documentFileName, this.fileHandler); //write file using fileHandler
					service.saveOrUpdate(customerCompanyFile); //persist model
				}


				return ResponseEntity
					  .ok()
					  .body(new FileCreated(documentFileName));
					  
		}catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
				   .badRequest()
				   .body(StringConverter.convertToReadable(GROUPID_NOTFOUND_ERROR_MESSAGE));
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

	@DeleteMapping("/delete/{customerCompanyFileId}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable("customerCompanyFileId") Integer customerCompanyFileId) {
      	
      	Date documentDate;

        try{            
            CustomerCompanyFile customerCompanyFile = service.get(customerCompanyFileId).orElseThrow();
            documentDate = customerCompanyFile.getDate();
           	CustomerGroup customerGroup = customerCompanyFile.getCustomerGroup();
           
            String path = CustomerCompanyDocsDirectorySourcePath
							  .getBasePath()
							  .concat("/")
							  .concat(String.valueOf(customerGroup.getId()).trim())
							  .concat("/")
							  .concat(this.stringifyDate(documentDate));

			this.fileHandler.setDirectorySourcePath(new CustomerCompanyDocsDirectorySourcePath(path));

			Boolean successfullyDelete = customerComapanyFileHandlerService.deleteDirectory(this.fileHandler);

            if(!successfullyDelete){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
			}
			
			service.delete(customerCompanyFile);
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body(StringConverter.convertToReadable(GROUPID_NOTFOUND_ERROR_MESSAGE));
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

	@DeleteMapping("/delete/{customerCompanyFileId}/{fileType}/{updatedBy}/{updatedAt}")
	public @ResponseBody ResponseEntity<?> delete(@RequestBody JsonNode requestBody,
												  @PathVariable("customerCompanyFileId") Integer customerCompanyFileId,
												  @PathVariable("fileType") String fileType,
												  @PathVariable("updatedBy") final int updatedById,
												  @PathVariable("updatedAt") final String updatedAtId) {

      	Date documentDate;
		String fileName = requestBody.get("filename").asText();
		

        try{            
			CustomerCompanyFile customerCompanyFile = service.get(customerCompanyFileId).orElseThrow();

			User updater = new User();
			updater.setId(updatedById);
			Program updatedAt = new Program();
			updatedAt.setId(updatedAtId);
			customerCompanyFile.setCreatedBy(updater);
			customerCompanyFile.setCreatedAt(updatedAt);

			FujikoFile typeOfFile = fileTypeDeterminer.determine(fileType);
						
			customerCompanyFile = typeOfFile.setFileNameModel(customerCompanyFile, null);

            documentDate = customerCompanyFile.getDate();
           	CustomerGroup customerGroup = customerCompanyFile.getCustomerGroup();
           
            String path = CustomerCompanyDocsDirectorySourcePath
							.getBasePath()
							.concat("/")
							.concat(String.valueOf(customerGroup.getId()).trim())
							.concat("/")
							.concat(this.stringifyDate(documentDate));

			this.fileHandler.setDirectorySourcePath(new CustomerCompanyDocsDirectorySourcePath(path));
			
			Boolean successfullyDelete = customerComapanyFileHandlerService.delete(fileName,this.fileHandler);
            if(!successfullyDelete){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
			}
			service.saveOrUpdate(customerCompanyFile);
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
            .notFound()
            .build();
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
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody CustomerCompanyFileCreateDTO customerCompanyFile, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			CustomerCompanyFile customerCompanyFileModel = this.modelMapper.map(customerCompanyFile,CustomerCompanyFile.class);

			service.saveOrUpdate(customerCompanyFileModel);
			String createdLink = "/api/customer/company/file/"+customerCompanyFileModel.getId();
			return ResponseEntity
				  .created(new URI(createdLink))
				  .body(new Created(createdLink));

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
	
	@PatchMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody CustomerCompanyFileUpdateDTO customerCompanyFileUpdate, Errors error) {
		if(error.hasErrors())
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<CustomerCompanyFile> customerCompanyFileRecord = service.get(customerCompanyFileUpdate.getId());
			CustomerCompanyFile customerCompanyFileModel=customerCompanyFileRecord.orElse(new CustomerCompanyFile());
			
			this.modelMapper
				.map(customerCompanyFileUpdate,customerCompanyFileModel);
			
			service.saveOrUpdate(customerCompanyFileModel);
			return ResponseEntity.ok().body(new Success());

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
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}
	}

	private @ResponseBody ResponseEntity<?> throwDocumentDateDuplicateError(){
		fte.api.Errors error = new fte.api.Errors();
		error.setDefaultMessage(DOCUMENT_DATE_DUPLICATE_ERROR_MESSAGE);
		error.setField("documentDate");		
		return ResponseEntity.badRequest().body(error);
	}

	private @ResponseBody ResponseEntity<?> throwDocumentDateNotFoundError(){
		fte.api.Errors error = new fte.api.Errors();
		error.setDefaultMessage(DOCUMENT_DATE_NOTFOUND_ERROR_MESSAGE);
		error.setField("documentDate");		
		return ResponseEntity.badRequest().body(error);
	}

	/***
	 * Creates a document filename
	 * @param baseName
	 * @param documentDate
	 * @param customerGroup
	 * @return filename
	 */
	//sample output: 企業調査_99999_123_201801
	private String createDocumentFileName(String baseName,Date documentDate,CustomerGroup customerGroup){
		final String NAME_SEPARATOR="_";
		String customerGroupCode = customerGroup.getCode().trim();
		String customerGroupId = String.valueOf(customerGroup.getId()).trim();
		String documentYearAndMonth = this.concatenateYearAndMonthAsString(documentDate);
		return baseName
			  .concat(NAME_SEPARATOR)
			  .concat(customerGroupCode)
			  .concat(NAME_SEPARATOR)
			  .concat(customerGroupId)
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
	/***
	 * Transform date into stringified yyyy-MM-dd format
	 * @param date
	 * @return Stringified date in format yyyy-MM-dd
	 */
	private String stringifyDate(Date date){
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");		
		return dateFormatter.format(date);
	}

	private void deleteFile(String fileName,CustomerCompanyDocsFileHandler fileHandler){
		try {
			fileHandler.delete(fileName);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
}