package w.fujiko.dto.gradeclassifications;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeClassificationUpdateDTO implements Serializable {

	private static final long serialVersionUID = 8178202286852309539L;

    @NotNull
	private short id;
	
    @JsonProperty("makerId")
    @NotNull
	private int makerId;
    
    @NotNull
	private String name;
	
	private Boolean isEnd=false;

    private Date dateUpdated = new Date();
    
    @JsonProperty("updatedBy")
    @NotNull
    private Integer updatedById;
    
    @JsonProperty("updatedAt")
    @NotNull
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}

}