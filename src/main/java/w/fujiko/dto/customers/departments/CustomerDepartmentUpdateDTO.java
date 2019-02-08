/**
 * 
 */
package w.fujiko.dto.customers.departments;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.NumberFormat;

/**
 * 
 * @author yagami
 *
 */
public class CustomerDepartmentUpdateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @JsonProperty("id")
    @NotNull
    private Integer id;
    
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

    @JsonProperty("dateUpdated")
    @NotNull
    private Date dateUpdated=new Date();

    @JsonProperty("updatedBy")
    private Integer updatedById;

    @JsonProperty("updatedAt")
    private String updatedAtId;

    @JsonProperty("isEnd")
	private Boolean isEnd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}
}