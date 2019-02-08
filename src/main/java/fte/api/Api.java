/**
 * 
 */
package fte.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import fte.api.universal.UniversalCrud;

/**
 * @author Try-Parser
 *
 */
public abstract class Api<T extends UniversalCrud<C, I>, C extends Serializable, I extends Serializable> implements ApiDefaults<C> {
	
	public @Autowired T service;

	@Override
	@GetMapping("/")
	public @ResponseBody List<C> get() {
		return service.get();
	}
	
	@Override
	@GetMapping("/{first}/{max}")
	public @ResponseBody Page<C> page(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		return service.paginate(first, max);
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<C> response = service.get((I)id);
		return response.isPresent()?
		ResponseEntity.ok().body(response.get()) : 
		ResponseEntity.badRequest().body("{ status: 404, message: Data not found. }");
	}
	
	@Override
	@PatchMapping("/")
	public @ResponseBody ResponseEntity<?> saveOrUpdate(@Valid @RequestBody C t, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			service.saveOrUpdate(t);
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

	@Override
	@DeleteMapping("/")
	public @ResponseBody ResponseEntity<?> delete(@Valid @RequestBody C t, Errors error) {
		// if(error.hasErrors()) 
		// return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			service.delete(t);
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
	
	@Override
	@DeleteMapping("disable/")
	public @ResponseBody ResponseEntity<?> disable(@Valid @RequestBody JsonNode body, Errors error) {
		if(error.hasErrors())
			return ResponseEntity.badRequest().body(error.getAllErrors());
		try{
			service.disable(true, (I) body.get("id"));		
			return ResponseEntity.ok().body(new Success());
		}catch(Exception ex){
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}	
	}

	@Override
	public Optional<UUID> uuidParsing(JsonNode id) {
		try {
			return Optional.of(UUID.fromString(id.asText()));
		} catch(Exception e) {
			return Optional.empty();
		}
	}
}
