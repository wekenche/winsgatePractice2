package w.fujiko.dto.genericclassifications.items;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericClassificationItemUpdateDTO implements Serializable {

	private static final long serialVersionUID = 8178202286852309539L;

	@NotNull
	private Integer id;
	
	@JsonProperty("genericClassification")
	@NotNull
	private Integer genericClassificationId;
	
	private String settingCharacter;
	
	private Boolean isEnd=false;
	
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

	public Integer getGenericClassificationId() {
		return genericClassificationId;
	}

	public void setGenericClassificationId(Integer genericClassificationId) {
		this.genericClassificationId = genericClassificationId;
	}

	public String getSettingCharacter() {
		return settingCharacter;
	}

	public void setSettingCharacter(String settingCharacter) {
		this.settingCharacter = settingCharacter;
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