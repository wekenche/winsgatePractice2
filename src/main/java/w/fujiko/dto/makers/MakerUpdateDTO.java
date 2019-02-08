package w.fujiko.dto.makers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MakerUpdateDTO implements Serializable {

	private static final long serialVersionUID = -1729484593930770855L;
    
    @NotNull
	private Integer id;
    
	private String name;
	
	private Boolean isEnd=false;

    @JsonIgnore
	private Date dateUpdated=new Date();

    @JsonProperty("updatedBy")
    @NotNull
    private Integer updatedById;
    
    @JsonProperty("updatedAt")
    @NotNull
	private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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