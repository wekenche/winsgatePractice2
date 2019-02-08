/**
 * 
 */
package w.fujiko.dto.billings;

import java.util.Date;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author yagami
 *
 */
public class BillingCreateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @NotNull
    @JsonProperty("code")
    private int code;

    @NotNull
    @JsonProperty("name")
    private String name;

	@JsonProperty("nameKana")
    private String nameKana;
    
	@JsonProperty("shortName")
    private String shortName;

	@JsonProperty("postalCode")
    private String postalCode;
    
    @JsonProperty("address1")
    private String address1;
    
    @JsonProperty("address2")
    private String address2;

    @JsonProperty("address3")
    private String address3;
    
    @JsonProperty("accountPhoneNumber")
    private String accountPhoneNumber;

    @JsonProperty("telephoneNumber1")
    private String telephoneNumber1;
	
    @JsonProperty("telephoneNumber2")
    private String telephoneNumber2;

    @JsonProperty("title")
    private Byte title;

	@JsonProperty("claimsGroup")
    private Integer claimsGroup;
    
    @JsonProperty("totalInvoicePrintingType")
    private Boolean totalInvoicePrintingType;
    
    @JsonProperty("billingPrintingType")
    private Boolean billingPrintingType;
    
    @JsonProperty("propertyPrintingType")
    private Boolean propertyPrintingType;
    
    @JsonProperty("depositMethod1reference")
    private Long depositMethod1reference;
    
    @JsonProperty("depositMethod1SplitPayment")
    private Byte depositMethod1SplitPayment;
    
    @JsonProperty("depositMethod1")
    private Byte depositMethod1;
    
    @JsonProperty("depositMethod1PlannedPaymentMonthAfter")
    private Integer depositMethod1PlannedPaymentMonthAfter;
    
    @JsonProperty("depositMethod1PlanDay")
    private Integer depositMethod1PlanDay;
    
    @JsonProperty("depositMethod1Division")
    private String depositMethod1Division;
    
    @JsonProperty("depositMethod1CriteriaPercentage")
    private Double depositMethod1CriteriaPercentage;
    
    @JsonProperty("depositMethod1DivisionStandardAmount")
    private Double depositMethod1DivisionStandardAmount;
    
    @JsonProperty("depositMethod1Slip")
    private Byte depositMethod1Slip;
    
    @JsonProperty("depositMethod1ScheduledPaymentMonthAfter")
    private Integer depositMethod1ScheduledPaymentMonthAfter;
    
    @JsonProperty("depositMethod1ScheduledDay")
    private Integer depositMethod1ScheduledDay;
    
    @JsonProperty("depositMethod2Reference")
    private Long depositMethod2Reference;
    
    @JsonProperty("depositMethod2SplitPayment")
    private Byte depositMethod2SplitPayment;
    
    @JsonProperty("depositMethod2")
    private Byte depositMethod2;
    
    @JsonProperty("depositMethod2PlannedPaymentMonthAfter")
    private Integer depositMethod2PlannedPaymentMonthAfter;
    
    @JsonProperty("depositMethod2PlanDay")
    private Integer depositMethod2PlanDay;
    
    @JsonProperty("depositMethod2Division")
    private String depositMethod2Division;
    
    @JsonProperty("depositMethod2CriteriaPercentage")
    private Double depositMethod2CriteriaPercentage;
    
    @JsonProperty("depositMethod2DivisionStandardAmount")
    private Double depositMethod2DivisionStandardAmount;
    
    @JsonProperty("depositMethod2Slip")
    private Byte depositMethod2Slip;
    
    @JsonProperty("depositMethod2ScheduledPaymentMonthAfter")
    private Integer depositMethod2ScheduledPaymentMonthAfter;
    
    @JsonProperty("depositMethod2ScheduledPlanDay")
    private Integer depositMethod2ScheduledPlanDay;
    
    @JsonProperty("cashOnDelivery")
    private Boolean cashOnDelivery;
    
    @JsonProperty("collection")
    private Boolean collection;
    
    @JsonProperty("depositSite")
    private Integer depositSite;
    
    @JsonProperty("shipmentAfterPaymentConfirmed")
    private Boolean shipmentAfterPaymentConfirmed;
    
    @JsonProperty("dedicatedSlip")
    private Boolean dedicatedSlip;
    
    @JsonProperty("paymentConditions")
	private String paymentConditions;
	
	@JsonProperty("collectSafetyCooperationFeeFlag")
	private Boolean collectSafetyCooperationFeeFlag = false;

	private Double collectSafetyCooperationFee = 0.00;

	@JsonProperty("collectSafetyCooperationFeeMemo")
	private String collectSafetyCooperationFeeMemo;
    
    @JsonProperty("memo")
    private String memo;

    @JsonProperty("isEnd")
    private Boolean isEnd;

    @JsonIgnore
    private Date dateCreated = new Date();
    
	@JsonProperty("createdBy")
	@NotNull
	private Integer createdById;

	@JsonProperty("createdAt")
	@NotNull
	private String createdAtId;
    
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

	public String getAccountPhoneNumber() {
		return accountPhoneNumber;
	}

	public void setAccountPhoneNumber(String accountPhoneNumber) {
		this.accountPhoneNumber = accountPhoneNumber;
	}

	public String getTelephoneNumber1() {
		return telephoneNumber1;
	}

	public void setTelephoneNumber1(String telephoneNumber1) {
		this.telephoneNumber1 = telephoneNumber1;
	}

	public String getTelephoneNumber2() {
		return telephoneNumber2;
	}

	public void setTelephoneNumber2(String telephoneNumber2) {
		this.telephoneNumber2 = telephoneNumber2;
	}

	public Byte getTitle() {
		return title;
	}

	public void setTitle(Byte title) {
		this.title = title;
	}

	public Integer getClaimsGroup() {
		return claimsGroup;
	}

	public void setClaimsGroup(Integer claimsGroup) {
		this.claimsGroup = claimsGroup;
	}

	public Boolean getTotalInvoicePrintingType() {
		return totalInvoicePrintingType;
	}

	public void setTotalInvoicePrintingType(Boolean totalInvoicePrintingType) {
		this.totalInvoicePrintingType = totalInvoicePrintingType;
	}

	public Boolean getBillingPrintingType() {
		return billingPrintingType;
	}

	public void setBillingPrintingType(Boolean billingPrintingType) {
		this.billingPrintingType = billingPrintingType;
	}

	public Boolean getPropertyPrintingType() {
		return propertyPrintingType;
	}

	public void setPropertyPrintingType(Boolean propertyPrintingType) {
		this.propertyPrintingType = propertyPrintingType;
	}

	public Long getDepositMethod1reference() {
		return depositMethod1reference;
	}

	public void setDepositMethod1reference(Long depositMethod1reference) {
		this.depositMethod1reference = depositMethod1reference;
	}

	public Byte getDepositMethod1SplitPayment() {
		return depositMethod1SplitPayment;
	}

	public void setDepositMethod1SplitPayment(Byte depositMethod1SplitPayment) {
		this.depositMethod1SplitPayment = depositMethod1SplitPayment;
	}

	public Byte getDepositMethod1() {
		return depositMethod1;
	}

	public void setDepositMethod1(Byte depositMethod1) {
		this.depositMethod1 = depositMethod1;
	}

	public Integer getDepositMethod1PlannedPaymentMonthAfter() {
		return depositMethod1PlannedPaymentMonthAfter;
	}

	public void setDepositMethod1PlannedPaymentMonthAfter(Integer depositMethod1PlannedPaymentMonthAfter) {
		this.depositMethod1PlannedPaymentMonthAfter = depositMethod1PlannedPaymentMonthAfter;
	}

	public Integer getDepositMethod1PlanDay() {
		return depositMethod1PlanDay;
	}

	public void setDepositMethod1PlanDay(Integer depositMethod1PlanDay) {
		this.depositMethod1PlanDay = depositMethod1PlanDay;
	}

	public String getDepositMethod1Division() {
		return depositMethod1Division;
	}

	public void setDepositMethod1Division(String depositMethod1Division) {
		this.depositMethod1Division = depositMethod1Division;
	}

	public Double getDepositMethod1CriteriaPercentage() {
		return depositMethod1CriteriaPercentage;
	}

	public void setDepositMethod1CriteriaPercentage(Double depositMethod1CriteriaPercentage) {
		this.depositMethod1CriteriaPercentage = depositMethod1CriteriaPercentage;
	}

	public Double getDepositMethod1DivisionStandardAmount() {
		return depositMethod1DivisionStandardAmount;
	}

	public void setDepositMethod1DivisionStandardAmount(Double depositMethod1DivisionStandardAmount) {
		this.depositMethod1DivisionStandardAmount = depositMethod1DivisionStandardAmount;
	}

	public Byte getDepositMethod1Slip() {
		return depositMethod1Slip;
	}

	public void setDepositMethod1Slip(Byte depositMethod1Slip) {
		this.depositMethod1Slip = depositMethod1Slip;
	}

	public Integer getDepositMethod1ScheduledPaymentMonthAfter() {
		return depositMethod1ScheduledPaymentMonthAfter;
	}

	public void setDepositMethod1ScheduledPaymentMonthAfter(Integer depositMethod1ScheduledPaymentMonthAfter) {
		this.depositMethod1ScheduledPaymentMonthAfter = depositMethod1ScheduledPaymentMonthAfter;
	}

	public Integer getDepositMethod1ScheduledDay() {
		return depositMethod1ScheduledDay;
	}

	public void setDepositMethod1ScheduledDay(Integer depositMethod1ScheduledDay) {
		this.depositMethod1ScheduledDay = depositMethod1ScheduledDay;
	}

	public Long getDepositMethod2Reference() {
		return depositMethod2Reference;
	}

	public void setDepositMethod2Reference(Long depositMethod2Reference) {
		this.depositMethod2Reference = depositMethod2Reference;
	}

	public Byte getDepositMethod2SplitPayment() {
		return depositMethod2SplitPayment;
	}

	public void setDepositMethod2SplitPayment(Byte depositMethod2SplitPayment) {
		this.depositMethod2SplitPayment = depositMethod2SplitPayment;
	}

	public Byte getDepositMethod2() {
		return depositMethod2;
	}

	public void setDepositMethod2(Byte depositMethod2) {
		this.depositMethod2 = depositMethod2;
	}

	public Integer getDepositMethod2PlannedPaymentMonthAfter() {
		return depositMethod2PlannedPaymentMonthAfter;
	}

	public void setDepositMethod2PlannedPaymentMonthAfter(Integer depositMethod2PlannedPaymentMonthAfter) {
		this.depositMethod2PlannedPaymentMonthAfter = depositMethod2PlannedPaymentMonthAfter;
	}

	public Integer getDepositMethod2PlanDay() {
		return depositMethod2PlanDay;
	}

	public void setDepositMethod2PlanDay(Integer depositMethod2PlanDay) {
		this.depositMethod2PlanDay = depositMethod2PlanDay;
	}

	public String getDepositMethod2Division() {
		return depositMethod2Division;
	}

	public void setDepositMethod2Division(String depositMethod2Division) {
		this.depositMethod2Division = depositMethod2Division;
	}

	public Double getDepositMethod2CriteriaPercentage() {
		return depositMethod2CriteriaPercentage;
	}

	public void setDepositMethod2CriteriaPercentage(Double depositMethod2CriteriaPercentage) {
		this.depositMethod2CriteriaPercentage = depositMethod2CriteriaPercentage;
	}

	public Double getDepositMethod2DivisionStandardAmount() {
		return depositMethod2DivisionStandardAmount;
	}

	public void setDepositMethod2DivisionStandardAmount(Double depositMethod2DivisionStandardAmount) {
		this.depositMethod2DivisionStandardAmount = depositMethod2DivisionStandardAmount;
	}

	public Byte getDepositMethod2Slip() {
		return depositMethod2Slip;
	}

	public void setDepositMethod2Slip(Byte depositMethod2Slip) {
		this.depositMethod2Slip = depositMethod2Slip;
	}

	public Integer getDepositMethod2ScheduledPaymentMonthAfter() {
		return depositMethod2ScheduledPaymentMonthAfter;
	}

	public void setDepositMethod2ScheduledPaymentMonthAfter(Integer depositMethod2ScheduledPaymentMonthAfter) {
		this.depositMethod2ScheduledPaymentMonthAfter = depositMethod2ScheduledPaymentMonthAfter;
	}

	public Integer getDepositMethod2ScheduledPlanDay() {
		return depositMethod2ScheduledPlanDay;
	}

	public void setDepositMethod2ScheduledPlanDay(Integer depositMethod2ScheduledPlanDay) {
		this.depositMethod2ScheduledPlanDay = depositMethod2ScheduledPlanDay;
	}

	public Boolean getCashOnDelivery() {
		return cashOnDelivery;
	}

	public void setCashOnDelivery(Boolean cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}

	public Boolean getCollection() {
		return collection;
	}

	public void setCollection(Boolean collection) {
		this.collection = collection;
	}

	public Integer getDepositSite() {
		return depositSite;
	}

	public void setDepositSite(Integer depositSite) {
		this.depositSite = depositSite;
	}

	public Boolean getShipmentAfterPaymentConfirmed() {
		return shipmentAfterPaymentConfirmed;
	}

	public void setShipmentAfterPaymentConfirmed(Boolean shipmentAfterPaymentConfirmed) {
		this.shipmentAfterPaymentConfirmed = shipmentAfterPaymentConfirmed;
	}

	public Boolean getDedicatedSlip() {
		return dedicatedSlip;
	}

	public void setDedicatedSlip(Boolean dedicatedSlip) {
		this.dedicatedSlip = dedicatedSlip;
	}

	public String getPaymentConditions() {
		return paymentConditions;
	}

	public void setPaymentConditions(String paymentConditions) {
		this.paymentConditions = paymentConditions;
	}

	public Boolean getCollectSafetyCooperationFeeFlag() {
		return collectSafetyCooperationFeeFlag;
	}

	public void setCollectSafetyCooperationFeeFlag(Boolean collectSafetyCooperationFeeFlag) {
		this.collectSafetyCooperationFeeFlag = collectSafetyCooperationFeeFlag;
	}

	public Double getCollectSafetyCooperationFee() {
		return collectSafetyCooperationFee;
	}

	public void setCollectSafetyCooperationFee(Double collectSafetyCooperationFee) {
		this.collectSafetyCooperationFee = collectSafetyCooperationFee;
	}

	public String getCollectSafetyCooperationFeeMemo() {
		return collectSafetyCooperationFeeMemo;
	}

	public void setCollectSafetyCooperationFeeMemo(String collectSafetyCooperationFeeMemo) {
		this.collectSafetyCooperationFeeMemo = collectSafetyCooperationFeeMemo;
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
    
    public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

	public String getCreatedAtId() {
		return this.createdAtId;
	}
}