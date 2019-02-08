package w.fujiko.dto.roleprograms.commands;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RoleProgramCommandDTO implements Serializable{

    private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("userId")
    @NotNull
    private Integer userId;
    
    @JsonProperty("programId")
    @NotNull
    private String programId;

    @NotNull
    @JsonProperty("commandId")    
    private Integer commandId;

    @JsonProperty("dateCreated")
    private Date dateCreated = new Date();

    @NotNull
    @JsonProperty("createdBy")
    private Integer createdById;

    @NotNull
    @JsonProperty("createdAt")
    private String createdAtId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId=userId;
    }

    public String getProgramId(){
        return programId;
    }

    public void setProgramId(String programId){
        this.programId=programId;
    }

    public Integer getCommandId(){
        return commandId;
    }
    
    public void setCommandId(Integer commandId){
        this.commandId=commandId;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated){
        this.dateCreated=dateCreated;
    }

    public Integer getCreatedById(){
        return createdById;
    }

    public void setCreatedById(Integer createdById){
        this.createdById=createdById;
    }

    public String getCreatedAtId(){
        return createdAtId;
    }
    public void setCreatedAtId(String createdAtId){
        this.createdAtId=createdAtId;
    }
}