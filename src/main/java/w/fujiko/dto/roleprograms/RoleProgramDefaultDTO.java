package w.fujiko.dto.roleprograms;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.programs.ProgramDTO;


public class RoleProgramDefaultDTO implements Serializable{

    private static final long serialVersionUID = 7257921460512063270L;

    @NotNull
    @JsonProperty("program")
    private ProgramDTO program;

    @JsonProperty("authorizedUserId")
    private Integer mstRoleProgAuthorized_user;

    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    @NotNull
    @JsonProperty("createdBy")
    private Integer createdById;

    @NotNull
    @JsonProperty("updatedBy")
    private Integer updatedById;

    @NotNull
    @JsonProperty("createdAt")
    private String createdAtId;

    @NotNull
    @JsonProperty("updatedAt")
    private String updatedAtId;    

    public ProgramDTO getProgram(){
        return program;
    }
    public void setProgram(ProgramDTO program){
        this.program=program;
    }

    public Integer getMstRoleProgAuthorized_user(){
        return mstRoleProgAuthorized_user;
    }

    public void setMstRoleProgAuthorized_user(Integer mstRoleProgAuthorized_user){
        this.mstRoleProgAuthorized_user=mstRoleProgAuthorized_user;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated){
        this.dateCreated=dateCreated;
    }

    public Date getDateUpdated(){
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated){
        this.dateUpdated=dateUpdated;
    }

    public Integer getCreatedById(){
        return createdById;
    }

    public void setCreatedBy(Integer createdById){
        this.createdById=createdById;
    }

    public Integer getUpdatedById(){
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById){
        this.updatedById=updatedById;
    }

    public String getCreatedAtId(){
        return createdAtId;
    }
    public void setCreatedAtId(String createdAtId){
        this.createdAtId=createdAtId;
    }

    public String getUpdatedAtId(){
        return updatedAtId;
    }
    public void setUpdatedAtId(String updatedAtId){
        this.updatedAtId=updatedAtId;
    }

}