package w.fujiko.dto.roleprograms;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RoleProgramDTO implements Serializable{

    private static final long serialVersionUID = 7257921460512063270L;

    @NotNull
    @JsonProperty("programId")
    private String program_id;

    @JsonProperty("authorizedUserId")
    private Integer authorized_user;

    @JsonProperty("dateCreated")
    private Date dateCreated = new Date();

    @JsonProperty("dateUpdated")
    private Date dateUpdated = new Date();

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

    public String getProgram_id(){
        return program_id;
    }
    public void setProgram_id(String program_id){
        this.program_id=program_id;
    }

    public Integer getAuthorized_user(){
        return authorized_user;
    }

    public void setAuthorized_user(Integer authorized_user){
        this.authorized_user=authorized_user;
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

    public void setCreatedById(Integer createdById){
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