package w.fujiko.dto.gradeclassifications;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeClassificationDTO implements Serializable {

	private static final long serialVersionUID = 8178202286852309539L;

	private short id;
	
	@JsonProperty("makerId")
	private int makerId;
    
    @JsonProperty("code")
	private short code;
	
	private String name;
	
	private Boolean isEnd;
	
	private Date dateCreated;

	private Date dateUpdated;

	@JsonProperty("createdBy")
	private Integer createdById;

	@JsonProperty("updatedBy")
	private Integer updatedById;

	@JsonProperty("createdAt")
	private String createdAtId;

	@JsonProperty("updatedAt")
	private String updatedAtId;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

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

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}

}