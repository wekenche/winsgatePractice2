package w.fujiko.api.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration.AccessLevel;
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
import fte.api.Success;
import w.fujiko.dto.roleprograms.RoleProgramDTO;
import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.model.masters.users.ProgramPk;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgramCommand;
import w.fujiko.service.users.MyMenuService;
import w.fujiko.service.users.RoleProgramCommandService;
import w.fujiko.service.users.RoleProgramService;
import w.fujiko.model.wrappers.RoleProgramWrapper;

@RestController
@RequestMapping("/api/role/program/")
public class RoleProgramApi 
    extends Api<RoleProgramService,RoleProgram,ProgramPk> {

        @Autowired 
        RoleProgramCommandService roleProgramCommandService;

        @Autowired 
        MyMenuService myMenuService;

        ModelMapper modelMapper;

        TypeMap<RoleProgramDTO, RoleProgram> defaultTypeMap;

        RoleProgramApi(){
            this.modelMapper  = new ModelMapper();
            this.modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PRIVATE)
            .setAmbiguityIgnored(true);
            defaultTypeMap = this.modelMapper
                            .createTypeMap(RoleProgramDTO.class,
                            RoleProgram.class);
        }

        @PostMapping("/grantprogramroles")
        public @ResponseBody ResponseEntity<?> saveAll(@Valid @RequestBody RoleProgramWrapper roleProgramWrapper, Errors error){
             if(error.hasErrors()) 
                return ResponseEntity.badRequest().body(error.getAllErrors());
            try {
                List<Response> responses=new ArrayList<>();                                
                for(RoleProgramDTO roleProgram : roleProgramWrapper.getRole_program()) {

                    RoleProgram roleProgramModel = mapDTOToModel(roleProgram);
                    service.saveOrUpdate(roleProgramModel);                                                                        
                    responses.add(new Success());
                }
                                                                                        
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

        @DeleteMapping("/revokeprogramroles")
        public @ResponseBody ResponseEntity<?> deleteAll(@Valid @RequestBody RoleProgramWrapper roleProgramWrapper, Errors error){
             if(error.hasErrors()) 
                return ResponseEntity.badRequest().body(error.getAllErrors());

            
            
            try {
                List<Response> responses=new ArrayList<>();                
                for(RoleProgramDTO roleProgram : roleProgramWrapper.getRole_program()) {
                    RoleProgram roleProgramModel = mapDTOToModel(roleProgram);
                    
                   //Get all child instances and delete
                   this.deleteChildInstances(roleProgramModel);

                   service.delete(roleProgramModel);
                   responses.add(new Success());
                }                                                           
                return ResponseEntity.ok().body(responses);
            } catch(Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
            }
        }

        /**Private Methods**/

        private void deleteChildInstances(RoleProgram roleProgram){
            this.deleteRoleProgramCommandInstances(roleProgram);
            this.deleteMyMenuInstances(roleProgram);
        }
        private void deleteRoleProgramCommandInstances(RoleProgram roleProgram){
            this.findRoleProgramCommandInstances(roleProgram).forEach(e->{
                roleProgramCommandService.delete(e);
            });
        }
        private void deleteMyMenuInstances(RoleProgram roleProgram){
            this.findMyMenuInstances(roleProgram).forEach(e->{
                myMenuService.delete(e);
            });
        }

        private List<RoleProgramCommand> findRoleProgramCommandInstances(RoleProgram roleProgram){
            return roleProgramCommandService.findByRoleProgram(roleProgram);
        }

        private List<MyMenu> findMyMenuInstances(RoleProgram roleProgram){
            return myMenuService.findByRoleProgram(roleProgram);
        }

        private RoleProgram mapDTOToModel(RoleProgramDTO roleProgramDto){
            return  this.defaultTypeMap                                                
                    .addMappings(mapper->{
                        mapper.<String>map(src->src.getProgram_id(),(dest,v)->dest.getMstRoleProg().setProgram_id(v));
                        mapper.<String>map(src->src.getProgram_id(),(dest,v)->dest.getProgram().setId(v));
                        mapper.<Integer>map(src->src.getAuthorized_user(),(dest,v)->dest.getMstRoleProg().setAuthorized_user(v));
                        mapper.<Integer>map(src->src.getCreatedById(),(dest,v)->dest.getCreatedBy().setId(v));
                        mapper.<Integer>map(src->src.getUpdatedById(),(dest,v)->dest.getUpdatedBy().setId(v));
                        mapper.<String>map(src->src.getCreatedAtId(),(dest,v)->dest.getCreatedAt().setId(v));
                        mapper.<String>map(src->src.getUpdatedAtId(),(dest,v)->dest.getUpdatedAt().setId(v));
                    }).map(roleProgramDto);                                                                                                           
        }

    }