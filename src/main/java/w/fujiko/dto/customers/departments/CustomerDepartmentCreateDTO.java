/**
 * 
 */
package w.fujiko.dto.customers.departments;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.NumberFormat;

/**
 * 
 * @author yagami
 *
 */
public class CustomerDepartmentCreateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
	private Integer id;
	
    @NotNull
    @JsonProperty("code")
    private int code;
    
    @NotNull
    @JsonProperty("baseId")
	private int baseId;

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

    @JsonIgnore
    private Date dateCreated=new Date();

    @JsonProperty("createdBy")
    private Integer createdById;

    @JsonProperty("createdAt")
    private String createdAtId;

    @JsonProperty("isEnd")
	private Boolean isEnd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
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

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}
}