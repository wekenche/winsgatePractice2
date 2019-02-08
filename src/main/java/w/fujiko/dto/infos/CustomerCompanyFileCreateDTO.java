/**
 * 
 */
package w.fujiko.dto.infos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class CustomerCompanyFileCreateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

	@JsonProperty("customerGroupId")
	@NotNull
    private Integer customerGroupId;

	@JsonProperty("documentDate")
	@NotNull
    private Date date;

	@JsonIgnore
    private Date dateCreated=new Date();

	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;
	
	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;	

	public Integer getCustomerGroupId() {
		return customerGroupId;
	}

	public void setCustomerGroupId(Integer customerGroupId) {
		this.customerGroupId = customerGroupId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateCreated() {
		return dateCreated;
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