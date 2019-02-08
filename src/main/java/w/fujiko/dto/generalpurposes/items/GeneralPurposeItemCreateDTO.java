package w.fujiko.dto.generalpurposes.items;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralPurposeItemCreateDTO implements Serializable {

	private static final long serialVersionUID = 9194748554293489630L;
	
    @JsonProperty("code")
    @NotNull
    @NotBlank
    @NotEmpty
	private String code;
    
    @JsonProperty("generalPurposeId")
    @NotNull
    private Integer generalPurposeId;

    @JsonProperty("name")
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

	@JsonProperty("isEnd")
	private Boolean isEnd=false;
	
	@JsonIgnore
    private Date dateCreated=new Date();

    @JsonProperty("createdBy")
    @NotNull
	private Integer createdById;

    @JsonProperty("createdAt")
    @NotNull
    @NotBlank
    @NotEmpty
	private String createdAtId;
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public Integer getGeneralPurposeId() {
		return generalPurposeId;
	}

	public void setGeneralPurposeId(Integer generalPurposeId) {
		this.generalPurposeId = generalPurposeId;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
    }
    
	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

}