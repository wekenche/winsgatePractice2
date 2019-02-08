/**
 * 
 */
package w.fujiko.dto.branches;

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
public class BranchCreateDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
    
	private Integer id;
	
	@NotNull
	private Integer code;
	
	@NotNull
    private String name;

    private String nameKana;

	private String shortName;
	
    private Boolean salesFlag=false;

    private Integer sequence;
    
    private String postalCode;
    
    private String address1;

    private String address2;
    
    private String telephoneNumber;

    private String faxNumber;
    
	private Integer warehouseId;

    private String receiptSequenceNumber;

    private String receiptSymbol;
    
    private Boolean isIndependent=false;

	private Boolean isEnd = false;
    
    @JsonIgnore
    private Date dateCreated = new Date();

	@JsonProperty("createdBy")
	@NotNull
    private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
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

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getReceiptSequenceNumber() {
		return receiptSequenceNumber;
	}

	public void setReceiptSequenceNumber(String receiptSequenceNumber) {
		this.receiptSequenceNumber = receiptSequenceNumber;
	}

	public String getReceiptSymbol() {
		return receiptSymbol;
	}

	public void setReceiptSymbol(String receiptSymbol) {
		this.receiptSymbol = receiptSymbol;
	}

	public Boolean getIsIndependent() {
		return isIndependent;
	}

	public void setIsIndependent(Boolean isIndependent) {
		this.isIndependent = isIndependent;
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