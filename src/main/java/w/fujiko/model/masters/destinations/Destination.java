/**
 * 
 */
package w.fujiko.model.masters.destinations;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import w.fujiko.model.masters.customers.CustomerBase;

import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_spl_destination")
public class Destination extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@NotNull
	@Column(name="mst_dest_cd", columnDefinition = "int", updatable = false)
	private Integer code;
	
	@ManyToOne
	@JoinColumn(name="mst_cst_id", insertable=true,updatable=true,nullable=true)
    private CustomerBase customer;

    @NotNull
	@Column(name="dest_name1", columnDefinition = "nvarchar(50)")
    private String name1;

	@Column(name="dest_name2", columnDefinition = "nvarchar(50)")
    private String name2;

	@Column(name="dest_kana", columnDefinition = "nvarchar(50)")
    private String nameKana;

	@Column(name="dest_shortname", columnDefinition = "nvarchar(25)")
    private String shortName;

	@Column(name="dest_doc_name", columnDefinition = "nvarchar(50)")
    private String documentPrintName;

    @Column(name="postal_code", columnDefinition = "varchar(15)")
    private String postalCode;

    @Column(name="address1", columnDefinition = "nvarchar(250)")
    private String address1;

    @Column(name="address2", columnDefinition = "nvarchar(250)")
    private String address2;

    @Column(name="address3", columnDefinition = "nvarchar(250)")
    private String address3;

    @Column(name="phone_number", columnDefinition = "nvarchar(25)")
    private String phoneNumber;

    @Column(name="fax_number", columnDefinition = "varchar(25)")
    private String faxNumber;

    @Column(name="mail", columnDefinition = "nvarchar(30)")
    private String email;

    @Column(name="url", columnDefinition = "nvarchar(250)")
    private String url;

    @Column(name="distributor_name", columnDefinition = "nvarchar(25)")
    private String distributorName;

    @Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public CustomerBase getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBase customer) {
		this.customer = customer;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
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

	public String getDocumentPrintName() {
		return documentPrintName;
	}

	public void setDocumentPrintName(String documentPrintName) {
		this.documentPrintName = documentPrintName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
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

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
    }
    
    public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

}