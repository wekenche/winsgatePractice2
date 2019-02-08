/**
 * 
 */
package w.fujiko.dto.customers;

import java.io.Serializable;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import w.fujiko.dto.billings.BillingDTO;
import w.fujiko.dto.branches.BranchDTODefault;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class CustomerBaseDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
	
	public CustomerBaseDTO(){}
	
	public CustomerBaseDTO(CustomerGroupDTO group,String code){
		this.group = group; 
		this.code = code;
	}	

	@JsonProperty("id")
    private Integer id;
    
	@NotNull
	@JsonProperty("code")
    private String code;
	
	@JsonProperty("customerGroup")
	private CustomerGroupDTO group;

	@JsonProperty("bill")
    private BillingDTO bill;

	@JsonProperty("name")
    private String name;

	@JsonProperty("nameKana")
    private String nameKana;

	@JsonProperty("shortName")
    private String shortName;

	@JsonProperty("branch")
    private BranchDTODefault branch;

	@JsonProperty("postalCode")
    private String postalCode;

	@JsonProperty("address1")
    private String address1;

	@JsonProperty("address2")
    private String address2;

	@JsonProperty("address3")
    private String address3;
	
	@JsonProperty("phoneNumber1")
    private String phoneNumber1;

	@JsonProperty("phoneNumber2")
    private String phoneNumber2;

	@JsonProperty("phoneNumber3")
    private String phoneNumber3;

	@JsonProperty("faxNumber1")
    private String faxNumber1;

	@JsonProperty("faxNumber2")
    private String faxNumber2;

	@Email
	@JsonProperty("email")
    private String email;

	@JsonProperty("url")
    private String url;

	@JsonProperty("single")
    private Boolean single;

	@JsonProperty("contract")
    private String contract;
	
	@JsonProperty("memo")
    private String memo;

    @JsonProperty("isEnd")
	private Boolean isEnd;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CustomerGroupDTO getGroup() {
		return group;
	}

	public void setGroup(CustomerGroupDTO group) {
		this.group = group;
	}

	public BillingDTO getBill() {
		return bill;
	}

	public void setBill(BillingDTO bill) {
		this.bill = bill;
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

	public BranchDTODefault getBranch() {
		return branch;
	}

	public void setBranch(BranchDTODefault branch) {
		this.branch = branch;
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

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	public String getFaxNumber1() {
		return faxNumber1;
	}

	public void setFaxNumber1(String faxNumber1) {
		this.faxNumber1 = faxNumber1;
	}

	public String getFaxNumber2() {
		return faxNumber2;
	}

	public void setFaxNumber2(String faxNumber2) {
		this.faxNumber2 = faxNumber2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getSingle() {
		return single;
	}

	public void setSingle(Boolean single) {
		this.single = single;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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