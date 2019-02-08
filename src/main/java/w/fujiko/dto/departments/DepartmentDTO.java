/**
 * 
 */
package w.fujiko.dto.departments;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * @author yagami
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "id",scope=DepartmentDTO.class)
public class DepartmentDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    private Integer id;

    @NotNull
    private Integer branchId;

    @JsonProperty("parentDepartment")
    private DepartmentDTO parentDepartment;
    
    @JsonProperty("subDepartments")
    private List<DepartmentDTO> subDepartments;
	
	@NotNull	
	private String code;

	@NotNull
    private String name;
    
    @NotNull	
    @JsonProperty("nameKana")
    private String nameKana;

    @NotNull	
    @JsonProperty("shortName")
    private String shortName;
    
    @JsonProperty("salesFlag")
    private Boolean salesFlag;

    private Integer sequence;
    
    @NotNull
    @JsonProperty("postalCode")
    private String postalCode;
    
    @NotNull    
    private String address1;
    
    @NotNull
    private String address2;
    
    @NotNull
    @JsonProperty("telephoneNumber")
    private String telephoneNumber;
    
    @JsonProperty("faxNumber")
    private String faxNumber;
    
    @JsonProperty("receiptSequenceNumber")
    private String receiptSequenceNumber;

    @JsonProperty("receiptSymbol")
    private String receiptSymbol;

    @JsonProperty("isEnd")
    private Boolean isEnd;

    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    @JsonProperty("createdBy")
    private String createdByUsernameKana;

    @JsonProperty("updatedBy")
    private String updatedByUsernameKana;

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
    
    public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public DepartmentDTO getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(DepartmentDTO parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public List<DepartmentDTO> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(List<DepartmentDTO> subDepartments) {
		this.subDepartments = subDepartments;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = String.format("%04d", Integer.valueOf(code));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Boolean getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(Boolean salesFlag) {
		this.salesFlag = salesFlag;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getReceiptSequenceNumber() {
		return receiptSequenceNumber;
	}

	public void setReceipt_sequence_number(String receiptSequenceNumber) {
		this.receiptSequenceNumber = receiptSequenceNumber;
	}

	public String getReceiptSymbol() {
		return receiptSymbol;
	}

	public void setReceiptSymbol(String receiptSymbol) {
		this.receiptSymbol = receiptSymbol;
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

    public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
    }

    public String getCreatedByUsernameKana() {
		return createdByUsernameKana;
	}

	public void setCreatedByUsernameKana(String createdByUsernameKana) {
		this.createdByUsernameKana = createdByUsernameKana;
    }
  
    public String getUpdatedByUsernameKana() {
		return createdByUsernameKana;
	}

	public void setUpdatedByUsernameKana(String updatedByUsernameKana) {
		this.updatedByUsernameKana = updatedByUsernameKana;
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
