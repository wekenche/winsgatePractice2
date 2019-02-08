package w.fujiko.dto.genericclassifications.items;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.NumberFormat;

public class GenericClassificationItemCreateDTO implements Serializable {

	private static final long serialVersionUID = 8178202286852309539L;

    @JsonProperty("genericClassificationId")
    @NotNull
	private Integer genericClassificationId;
    
    @JsonProperty("code")
	@NotNull
	@NumberFormat
	private int code;
	
	private String settingCharacter;
	
	private Boolean isEnd=false;
    
    @JsonIgnore
	private Date dateCreated = new Date();

    @JsonProperty("createdBy")
	@NotNull
	@NumberFormat
	private Integer createdById;

    @JsonProperty("createdAt")
    @NotNull
	private String createdAtId;

	public Integer getGenericClassificationId() {
		return genericClassificationId;
	}

	public void setGenericClassificationId(Integer genericClassificationId) {
		this.genericClassificationId = genericClassificationId;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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