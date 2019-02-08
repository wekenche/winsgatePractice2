package w.fujiko.api.users;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Response;
import w.fujiko.model.masters.users.RoleProgramCommand;
import w.fujiko.service.users.RoleProgramCommandService;
import w.fujiko.util.mapper.propertymaps.users.roles.commands.DTOToRoleProgramCommandMap;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.wrappers.RoleProgramCommandWrapper;

@RestController
@RequestMapping("/api/role/program/command/")
public class RoleProgramCommandApi 
    extends Api<RoleProgramCommandService,RoleProgramCommand,RoleProgramCommandPk> {

        @Autowired
        RoleProgramCommandService roleProgramCommandService;

        @PostMapping("/grantprogramcommandroles")
        public @ResponseBody ResponseEntity<?> saveAll(@Valid @RequestBody RoleProgramCommandWrapper wrapper, Errors error){
            if(error.hasErrors()) 
                return ResponseEntity.badRequest().body(error.getAllErrors());

            try { 
                List<Response> responses=new ArrayList<>();
                var modelMapper = new ModelMapper();
                modelMapper.getConfiguration().setAmbiguityIgnored(true);
                modelMapper.addMappings(new DTOToRoleProgramCommandMap());

                Type listType = new TypeToken<List<RoleProgramCommand>>() {}.getType();
                List<RoleProgramCommand> grantedRoleProgramCommands = modelMapper.map(wrapper.getRole_program_commands(),listType);
                service.batchSave(grantedRoleProgramCommands);
                
                return ResponseEntity.ok().body(responses);
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

        @DeleteMapping("/revokeprogramcommandroles")
        public @ResponseBody ResponseEntity<?> deleteAll(@Valid @RequestBody RoleProgramCommandWrapper wrapper, Errors error){
            if(error.hasErrors()) 
                return ResponseEntity.badRequest().body(error.getAllErrors());
                        
            try {
                List<Response> responses=new ArrayList<>();
                var modelMapper = new ModelMapper();
                modelMapper.getConfiguration().setAmbiguityIgnored(true);
                modelMapper.addMappings(new DTOToRoleProgramCommandMap());

                Type listType = new TypeToken<List<RoleProgramCommand>>() {}.getType();
                List<RoleProgramCommand> revokedRoleProgramCommands = modelMapper.map(wrapper.getRole_program_commands(),listType);               
                service.batchDelete(revokedRoleProgramCommands);
                 
                return ResponseEntity.ok().body(responses);
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

    }