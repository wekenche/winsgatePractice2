package w.fujiko.dto.generalpurposes.items;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralPurposeItemUpdateDTO implements Serializable {

	private static final long serialVersionUID = 9194748554293489630L;
	
    @JsonProperty("id")
    @NotNull
	private Integer id;
    
    @JsonProperty("generalPurposeId")
    @NotNull
    private Integer generalPurposeId;
    
	@JsonProperty("name")
    private String name;

	@JsonProperty("isEnd")
	private Boolean isEnd;
	
	@JsonIgnore
    private Date dateUpdated = new Date();
    
    @JsonProperty("updatedBy")
    @NotNull
    private Integer updatedById;
    
    @JsonProperty("updatedAt")
    @NotNull
    @NotBlank
    @NotEmpty
	private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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