package w.fujiko.api.purchaseorders;

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
import w.fujiko.dto.purchaseorders.PurchaseOrderDTO;
import w.fujiko.dto.purchaseorders.details.PurchaseOrderDetailDTO;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.model.wrappers.PurchaseOrderInputWrapper;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderDetailService;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderHeaderService;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderInputService;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.purchaseorders.details.CreateDTOToPurchaseOrderDetailMap;
import w.fujiko.util.mapper.propertymaps.purchaseorders.details.PurchaseOrderDetailDTOMap;
import w.fujiko.util.mapper.propertymaps.purchaseorders.headers.CreateDTOToPurchaseOrderHeaderMap;
import w.fujiko.util.mapper.propertymaps.purchaseorders.headers.PurchaseOrderHeaderToDTOMap;

@RestController
@RequestMapping("/api/po")
public class PurchaseOrderInputApi {

	@Autowired PurchaseOrderInputService purchaseOrderInputService;
	@Autowired PurchaseOrderHeaderService purchaseOrderHeaderService;
	@Autowired PurchaseOrderDetailService purchaseOrderDetailService;
	
	private ModelMapper createDTOToPurchaseOrderHeaderMapper;
	private ModelMapper createDTOToPurchaseOrderDetailMapper;
	private ModelMapper purchaseOrderHeaderToDTOMapper;
	private ModelMapper purchaseOrderDetailToDTOMapper;

	public PurchaseOrderInputApi() {
		createDTOToPurchaseOrderHeaderMapper = new ModelMapper();
		createDTOToPurchaseOrderHeaderMapper.getConfiguration().setAmbiguityIgnored(true);
		
		createDTOToPurchaseOrderDetailMapper = new ModelMapper();	
		createDTOToPurchaseOrderDetailMapper.getConfiguration().setAmbiguityIgnored(true);

		purchaseOrderHeaderToDTOMapper = new ModelMapper();		
		purchaseOrderHeaderToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		
		purchaseOrderDetailToDTOMapper = new ModelMapper();	
		purchaseOrderDetailToDTOMapper.getConfiguration().setAmbiguityIgnored(true);

		createDTOToPurchaseOrderHeaderMapper.addMappings(new CreateDTOToPurchaseOrderHeaderMap());
		createDTOToPurchaseOrderDetailMapper.addMappings(new CreateDTOToPurchaseOrderDetailMap());
		purchaseOrderHeaderToDTOMapper.addMappings(new PurchaseOrderHeaderToDTOMap());
		purchaseOrderDetailToDTOMapper.addMappings(new PurchaseOrderDetailDTOMap());
	}
	
	@PostMapping
	public ResponseEntity<?> createPurchaseOrder(@Valid @RequestBody PurchaseOrderInputWrapper purchaseOrderInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			PurchaseOrderHeader purchaseOrderHeaderEntity = this.createDTOToPurchaseOrderHeaderMapper
                                                        .map(purchaseOrderInputWrapper.getHeader(), PurchaseOrderHeader.class);
			
            Type listType = new TypeToken<List<PurchaseOrderDetail>>() {}.getType();
            
            List<PurchaseOrderDetail> purchaseOrderDetails = this.createDTOToPurchaseOrderDetailMapper
                                                         .map(purchaseOrderInputWrapper.getDetails(),listType);
        
			PurchaseOrderTransaction transaction = new PurchaseOrderTransaction(purchaseOrderHeaderEntity, purchaseOrderDetails);
			purchaseOrderInputService.add(transaction);
			String createdLink = "/api/po/"+purchaseOrderHeaderEntity.getId();
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
	public ResponseEntity<?> PurchaseOrderDeleted(@Valid @RequestBody PurchaseOrderInputWrapper purchaseOrderInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			PurchaseOrderHeader purchaseOrderHeaderEntity = this.createDTOToPurchaseOrderHeaderMapper
                                                        .map(purchaseOrderInputWrapper.getHeader(), PurchaseOrderHeader.class);
			
            Type listType = new TypeToken<List<PurchaseOrderDetail>>() {}.getType();
            
            List<PurchaseOrderDetail> purchaseOrderDetails = this.createDTOToPurchaseOrderDetailMapper
                                                         .map(purchaseOrderInputWrapper.getDetails(),listType);
        
			PurchaseOrderTransaction transaction = new PurchaseOrderTransaction(purchaseOrderHeaderEntity, purchaseOrderDetails);
			purchaseOrderInputService.add(transaction);
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
	
	@GetMapping("/{slipNumber}")
	public @ResponseBody PurchaseOrderDTO getPurchaseOrderBySlipNumber(
			@PathVariable("slipNumber") final String slipNumber) {
		
		var purchaseOrderHeader = purchaseOrderHeaderService.getBySlipNumber(slipNumber).orElseThrow();
		var purchaseOrderDetails= purchaseOrderDetailService.getByPurchaseOrderHeaderId(purchaseOrderHeader.getId());
		var purchaseOrderDTO = new PurchaseOrderDTO();
		var purchaseOrderHeaderDTO = this.purchaseOrderHeaderToDTOMapper
									.map(purchaseOrderHeader, PurchaseOrderHeaderDTO.class);

		Type purchaseOrderDetailDTOListType = new TypeToken<List<PurchaseOrderDetailDTO>>() {}.getType();
		List<PurchaseOrderDetailDTO> purchaseOrderDetailsDTO = this.purchaseOrderDetailToDTOMapper
									  					   .map(purchaseOrderDetails, purchaseOrderDetailDTOListType);
															 
		purchaseOrderDTO.setPurchaseOrderHeader(purchaseOrderHeaderDTO);
		purchaseOrderDTO.setPurchaseOrderDetails(purchaseOrderDetailsDTO);

		return purchaseOrderDTO;
	}

}