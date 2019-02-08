package w.fujiko.dto.generalpurposes;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class GeneralPurposeCreateDTO implements Serializable {

	private static final long serialVersionUID = 9194748554293489630L;
	
    @JsonProperty("code")
    @NotNull
    @NotBlank
    @NotEmpty
	private String code;
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("isEnd")
	private Boolean isEnd;
	
	@JsonIgnore
    private Date dateCreated = new Date();
    
    @JsonProperty("createdBy")
    @NotNull
	private Integer createdById;

    @JsonProperty("createdAt")
    @NotNull
    @NotBlank
    private String createdAtId;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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