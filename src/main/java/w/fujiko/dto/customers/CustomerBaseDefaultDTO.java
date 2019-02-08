/**
 * 
 */
package w.fujiko.dto.customers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class CustomerBaseDefaultDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
	
	@JsonProperty("id")
    private Integer id;
    
	@JsonProperty("code")
    private String code;

    @JsonProperty("name")    
    private String name;

	@JsonProperty("billingId")
    private Integer billingId;

	@JsonProperty("branchId")
	private Integer branchId;
	
	@JsonProperty("customerGroup")
	private CustomerGroupDefaultDTO customerGroup;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBillingId() {
		return billingId;
	}

	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public CustomerGroupDefaultDTO getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(CustomerGroupDefaultDTO customerGroup) {
		this.customerGroup = customerGroup;
	}
}