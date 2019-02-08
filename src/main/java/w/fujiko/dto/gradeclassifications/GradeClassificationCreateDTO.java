package w.fujiko.dto.gradeclassifications;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeClassificationCreateDTO implements Serializable {

	private static final long serialVersionUID = 8178202286852309539L;
	
    @JsonProperty("makerId")
    @NotNull
	private Integer makerId;
    
	@JsonProperty("code")
	@NotNull
	private short code;
	
	@NotEmpty
	private String name;
	
	private Boolean isEnd=false;
	
	private Date dateCreated=new Date();

	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;

	public int getMakerId() {
		return makerId;
	}

	public void setMakerId(int makerId) {
		this.makerId = makerId;
	}

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
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