package w.fujiko.model.infos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fte.api.Defaults;
import w.fujiko.model.masters.customers.CustomerGroup;

@Entity
@Table(name="info_cst_company_file")
public class CustomerCompanyFile extends Defaults implements Serializable{

    private static final long serialVersionUID = 1L;

    @JoinColumn(name="mst_cst_group_id",insertable=true,updatable=true)
    @NotNull
    @ManyToOne
    private CustomerGroup customerGroup;

    @Column(name="document_date",columnDefinition="date")
    @NotNull
    private Date date; 

    @Column(name="transaction_approval_doc_filename",columnDefinition="nvarchar(35)")
    private String transactionApprovalFilename;

    @Column(name="company_survey_doc_filename",columnDefinition="nvarchar(35)")
    private String companySurveyFilename;

    @Column(name="transaction_register_doc_filename",columnDefinition="nvarchar(35)")
    private String transactionRegisterFilename;

    @Column(name="address_change_doc_filename",columnDefinition="nvarchar(35)")
    private String addressChangeFilename;

    @Column(name="others_doc_filename",columnDefinition="nvarchar(35)")
    private String othersDocFilename;

    @Column(name="old_sales_management_doc_filename",columnDefinition="nvarchar(35)")
    private String oldSalesManagementDocFilename;

	public CustomerGroup getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(CustomerGroup customerGroup) {
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
}