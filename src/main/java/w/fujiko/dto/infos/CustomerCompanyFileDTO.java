/**
 * 
 */
package w.fujiko.dto.infos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.customers.CustomerGroupDTO;

/**
 * 
 * @author yagami
 *
 */
public class CustomerCompanyFileDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("customerGroup")
    private CustomerGroupDTO customerGroup;

    @JsonProperty("documentDate")
    private Date date;

    @JsonProperty("transactionApprovalFilename")
    private String transactionApprovalFilename;

    @JsonProperty("companySurveyFilename")
    private String companySurveyFilename;

    @JsonProperty("transactionRegisterFilename")
    private String transactionRegisterFilename;

    @JsonProperty("addressChangeFilename")
    private String addressChangeFilename;

    @JsonProperty("othersDocFilename")
    private String othersDocFilename;

    @JsonProperty("oldSalesManagementDocFilename")
    private String oldSalesManagementDocFilename;

    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    @JsonProperty("createdBy")
    private Integer createdById;

    @JsonProperty("updatedBy")
    private Integer updatedById;

    @JsonProperty("createdAt")
    private String createdAtId;

    @JsonProperty("updatedAt")
    private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerGroupDTO getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(CustomerGroupDTO customerGroup) {
		this.customerGroup = customerGroup;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionApprovalFilename() {
		return transactionApprovalFilename;
	}

	public void setTransactionApprovalFilename(String transactionApprovalFilename) {
		this.transactionApprovalFilename = transactionApprovalFilename;
	}

	public String getCompanySurveyFilename() {
		return companySurveyFilename;
	}

	public void setCompanySurveyFilename(String companySurveyFilename) {
		this.companySurveyFilename = companySurveyFilename;
	}

	public String getTransactionRegisterFilename() {
		return transactionRegisterFilename;
	}

	public void setTransactionRegisterFilename(String transactionRegisterFilename) {
		this.transactionRegisterFilename = transactionRegisterFilename;
	}

	public String getAddressChangeFilename() {
		return addressChangeFilename;
	}

	public void setAddressChangeFilename(String addressChangeFilename) {
		this.addressChangeFilename = addressChangeFilename;
	}

	public String getOthersDocFilename() {
		return othersDocFilename;
	}

	public void setOthersDocFilename(String othersDocFilename) {
		this.othersDocFilename = othersDocFilename;
	}

	public String getOldSalesManagementDocFilename() {
		return oldSalesManagementDocFilename;
	}

	public void setOldSalesManagementDocFilename(String oldSalesManagementDocFilename) {
		this.oldSalesManagementDocFilename = oldSalesManagementDocFilename;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}
}