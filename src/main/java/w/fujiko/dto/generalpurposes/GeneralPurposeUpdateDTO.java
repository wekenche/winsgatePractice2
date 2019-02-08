package w.fujiko.dto.generalpurposes;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

public class GeneralPurposeUpdateDTO implements Serializable {

	private static final long serialVersionUID = 9194748554293489630L;
	
    @JsonProperty("id")
    @NotNull
	private Integer id;
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("isEnd")
	private Boolean isEnd;

	@JsonIgnore
    private Date dateUpdated=new Date();
    
    @JsonProperty("updatedBy")
    @NotNull
	private Integer updatedById;

	@JsonProperty("updatedAt")
    @NotBlank
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