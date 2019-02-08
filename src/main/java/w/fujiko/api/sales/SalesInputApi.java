package w.fujiko.api.sales;

import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Created;
import fte.api.Response;
import fte.api.Success;

import w.fujiko.dto.sales.SalesDTO;
import w.fujiko.dto.sales.details.SalesDetailDTO;
import w.fujiko.dto.sales.headers.SalesHeaderDTO;
import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.model.wrappers.SalesInputWrapper;
import w.fujiko.service.transactions.sales.SalesDetailService;
import w.fujiko.service.transactions.sales.SalesHeaderService;
import w.fujiko.service.transactions.sales.SalesInputService;
import w.fujiko.util.common.sales.SalesTransaction;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.sales.details.CreateDTOToSalesDetailMap;
import w.fujiko.util.mapper.propertymaps.sales.details.SalesDetailToDTOMap;
import w.fujiko.util.mapper.propertymaps.sales.headers.CreateDTOToSalesHeaderMap;
import w.fujiko.util.mapper.propertymaps.sales.headers.SalesHeaderToDTOMap;

@RestController
@RequestMapping("/api/sales")
public class SalesInputApi {

	@Autowired SalesInputService salesInputService;
	@Autowired SalesHeaderService salesHeaderService;
	@Autowired SalesDetailService salesDetailService;
	
	private ModelMapper createDTOToSalesHeaderMapper;
	private ModelMapper createDTOToSalesDetailMapper;
	private ModelMapper salesHeaderToDTOMapper;
	private ModelMapper salesDetailToDTOMapper;

	public SalesInputApi() {
		createDTOToSalesHeaderMapper = new ModelMapper();
		createDTOToSalesHeaderMapper.getConfiguration().setAmbiguityIgnored(true);
		
		createDTOToSalesDetailMapper = new ModelMapper();	
		createDTOToSalesDetailMapper.getConfiguration().setAmbiguityIgnored(true);

		salesHeaderToDTOMapper = new ModelMapper();		
		salesHeaderToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		
		salesDetailToDTOMapper = new ModelMapper();	
		salesDetailToDTOMapper.getConfiguration().setAmbiguityIgnored(true);

		createDTOToSalesHeaderMapper.addMappings(new CreateDTOToSalesHeaderMap());
		createDTOToSalesDetailMapper.addMappings(new CreateDTOToSalesDetailMap());
		salesHeaderToDTOMapper.addMappings(new SalesHeaderToDTOMap());
		salesDetailToDTOMapper.addMappings(new SalesDetailToDTOMap());
	}
	
	@PostMapping
	public ResponseEntity<?> createSales(@Valid @RequestBody SalesInputWrapper salesInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			SalesHeader salesHeaderEntity = this.createDTOToSalesHeaderMapper
                                                .map(salesInputWrapper.getHeader(), SalesHeader.class);
			
            Type listType = new TypeToken<List<SalesDetail>>() {}.getType();
            
            List<SalesDetail> salesDetails = this.createDTOToSalesDetailMapper
                                                 .map(salesInputWrapper.getDetails(),listType);
                    
			var transaction = new SalesTransaction(salesHeaderEntity, salesDetails);
			salesInputService.add(transaction);
			String createdLink = "/api/sales/"+salesHeaderEntity.getId();
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
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> PurchaseOrderDeleted(@Valid @RequestBody SalesInputWrapper salesInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			SalesHeader salesHeaderEntity = this.createDTOToSalesHeaderMapper
                                                .map(salesInputWrapper.getHeader(), SalesHeader.class);
			
            Type listType = new TypeToken<List<SalesDetail>>() {}.getType();
            
            List<SalesDetail> salesDetails = this.createDTOToSalesDetailMapper
                                                 .map(salesInputWrapper.getDetails(),listType);
        
			SalesTransaction transaction = new SalesTransaction(salesHeaderEntity, salesDetails);
			salesInputService.add(transaction);
			return ResponseEntity
				  .ok(new Success());

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
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@GetMapping("/{salesHeaderId}")
	public @ResponseBody SalesDTO getSalesByPurchaseOrderHeaderId(
			@PathVariable("salesHeaderId") final Integer salesHeaderId) {
		
		var salesHeader = salesHeaderService.get(salesHeaderId).orElseThrow();
		var salesDetails= salesDetailService.getBySalesHeaderId(salesHeaderId);
		var salesDTO = new SalesDTO();
		var salesHeaderDTO = this.salesHeaderToDTOMapper
                                 .map(salesHeader, SalesHeaderDTO.class);

		Type salesDetailDTOListType = new TypeToken<List<SalesDetailDTO>>() {}.getType();
		List<SalesDetailDTO> salesDetailsDTO = this.salesDetailToDTOMapper
                                                   .map(salesDetails, salesDetailDTOListType);
															 
		salesDTO.setSalesHeader(salesHeaderDTO);
		salesDTO.setSalesDetails(salesDetailsDTO);

		return salesDTO;
	}

}