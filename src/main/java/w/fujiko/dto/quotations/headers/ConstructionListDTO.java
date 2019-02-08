package w.fujiko.dto.quotations.headers;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import w.fujiko.dto.properties.PropertyDefaultDTO;
import w.fujiko.dto.users.UserDefaultDTO;
import w.fujiko.dto.customers.CustomerBaseDefaultDTO;

public class ConstructionListDTO implements Serializable {

	private static final long serialVersionUID = -2665507327289852131L;
	
	@JsonProperty("workingSite")
	private PropertyDefaultDTO workingSite;

	@JsonProperty("quotationDate")
	private Date quotationDate;

	@JsonProperty("constructionNumber")
	private String constructionNumber;

	@JsonProperty("constructionName")
	private String constructionName;

	@JsonProperty("user")
	private UserDefaultDTO user;

	@JsonProperty("customerBase")
	private CustomerBaseDefaultDTO customerBase;

    @JsonProperty("customerGroupId")
    private Integer customerBaseGroupId;

    @JsonProperty("customerGroupCode")
    private String customerBaseGroupCode;

    @JsonProperty("customerGroupName")
    private String customerBaseGroupName;

	public PropertyDefaultDTO getWorkingSite() {
		return workingSite;
	}

	public void setWorkingSite(PropertyDefaultDTO workingSite) {
		this.workingSite = workingSite;
	}
	
	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public String getConstructionNumber() {
		return constructionNumber;
	}

	public void setConstructionNumber(String constructionNumber) {
		this.constructionNumber = constructionNumber;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public UserDefaultDTO getUser() {
		return user;
	}

	public void setUser(UserDefaultDTO user) {
		this.user = user;
	}

	public CustomerBaseDefaultDTO getCustomerBase() {
		return customerBase;
	}

	public void setCustomerBase(CustomerBaseDefaultDTO customerBase) {
		this.customerBase = customerBase;
    }
    
    public Integer getCustomerBaseGroupId() {
		return customerBaseGroupId;
	}

	public void setCustomerBaseGroupId(Integer customerBaseGroupId) {
		this.customerBaseGroupId = customerBaseGroupId;
	}

    public String getcustomerBaseGroupCode() {
		return customerBaseGroupCode;
	}

	public void setcustomerBaseGroupCode(String customerBaseGroupCode) {
		this.customerBaseGroupCode = customerBaseGroupCode;
    }
    
    public String getCustomerBaseGroupName() {
		return customerBaseGroupName;
	}

	public void setCustomerBaseGroupName(String customerBaseGroupName) {
		this.customerBaseGroupName = customerBaseGroupName;
	}
}