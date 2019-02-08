package w.fujiko.dto.makers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MakerCreateDTO implements Serializable {

	private static final long serialVersionUID = -1729484593930770855L;
    
    @NotNull
	private Integer code;
    
	private String name;
	
	private Boolean isEnd=false;

    @JsonIgnore
	private Date dateCreated=new Date();

    @JsonProperty("createdBy")
    @NotNull
	private Integer createdById;

    @JsonProperty("createdAt")
    @NotNull
	private String createdAtId;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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