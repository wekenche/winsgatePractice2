package w.fujiko.dto.suppliers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierBaseDefaultDTO implements Serializable {

	private static final long serialVersionUID = -6851663819757575021L;
	
	@JsonProperty("id")
    private Integer id;
    
	@JsonProperty("code")
    private String code;

    @JsonProperty("name")    
    private String name;

	@JsonProperty("paymentId")
    private Integer paymentId;

	@JsonProperty("branchId")
	private Integer branchId;
	
	@JsonProperty("supplierGroup")
	private SupplierGroupDefaultDTO supplierGroup;

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

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public SupplierGroupDefaultDTO getSupplierGroup() {
		return supplierGroup;
	}

	public void setSupplierGroup(SupplierGroupDefaultDTO supplierGroup) {
		this.supplierGroup = supplierGroup;
	}

}