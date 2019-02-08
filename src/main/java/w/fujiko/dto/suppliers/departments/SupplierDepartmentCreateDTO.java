package w.fujiko.dto.suppliers.departments;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierDepartmentCreateDTO implements Serializable {

	private static final long serialVersionUID = -4008901262747064944L;

	private Integer id;
	
    @NotNull
    @JsonProperty("code")
    private Integer code;
    
    @NotNull
    @JsonProperty("baseId")
	private Integer baseId;

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("industry")
    private String industry;
    
    @JsonProperty("phoneNumber")
    @NumberFormat
    private String phoneNumber;

    @JsonProperty("extension")
    private String extension;
    
    @JsonProperty("isEnd")
	private Boolean isEnd;

    @JsonIgnore
    private Date dateCreated=new Date();

    @JsonProperty("createdBy")
    private Integer createdById;

    @JsonProperty("createdAt")
    private String createdAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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