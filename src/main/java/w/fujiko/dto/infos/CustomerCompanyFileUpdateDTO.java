/**
 * 
 */
package w.fujiko.dto.infos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class CustomerCompanyFileUpdateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("customerGroupId")
	@NotNull
    private Integer customerGroupId;

    @JsonProperty("documentDate")
    @NotNull
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

    @JsonProperty("dateUpdated")
    private Date dateUpdated = new Date();

    @JsonProperty("updatedBy")
    private Integer updatedById;

    @JsonProperty("updatedAt")
    private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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