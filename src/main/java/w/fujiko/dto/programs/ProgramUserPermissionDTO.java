package w.fujiko.dto.programs;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import w.fujiko.model.masters.users.RoleProgram;

public class ProgramUserPermissionDTO implements Serializable{

    private static final long serialVersionUID = 7257921460512063270L;

    private String id;

    private String text;

    private Boolean hasPermission;

    @JsonIgnore
    private Set<RoleProgram> roles;

    public String getId(){
        return id;
    }
    public String setId(String id){
        return this.id=id;
    }
    public String getText(){
        return text;
    }
    public String setText(String text){
        return this.text=text;
    }
    public Set<RoleProgram> getRoles(){
        return roles;
    }
    public void setRoles(Set<RoleProgram> roles){
        this.roles=roles;
        this.hasPermission = !this.roles.isEmpty();
    }
    
    public Boolean getHasPermission(){
        return this.hasPermission;
    }    
}