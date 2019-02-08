/**
 * 
 */
package w.fujiko.model.masters.billings;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_billing")
public class Billing extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
		
	@NotNull
	@Column(name="billing_code", columnDefinition = "int", updatable = false)
    private int code;

	@Column(name="billing_name", columnDefinition = "nvarchar(35)")
	@NotNull
    private String name;

	@Column(name="billing_name_kana", columnDefinition = "nvarchar(30)")
    private String nameKana;
    
	@Column(name="billing_shortname", columnDefinition = "nvarchar(25)")
    private String shortName;

	@Column(name="billing_postal_code", columnDefinition = "nvarchar(25)")
    private String postalCode;
    
    @Column(name="billing_address1", columnDefinition = "nvarchar(50)")
    private String address1;
    
    @Column(name="billing_address2", columnDefinition = "nvarchar(50)")
    private String address2;

    @Column(name="billing_address3", columnDefinition = "nvarchar(50)")
    private String address3;
    
    @Column(name="billing_acc_phoneno", columnDefinition = "varchar(25)")
    private String accountPhoneNumber;

    @Column(name="billing_telno1", columnDefinition = "varchar(15)")
    private String telephoneNumber1;
	
    @Column(name="billing_telno2", columnDefinition = "varchar(15)")
    private String telephoneNumber2;

    @Column(name="billing_title", columnDefinition = "tinyint")
    private Byte title;

	@Column(name="billing_claims_group", columnDefinition = "int")
    private Integer claimsGroup;
    
    @Column(name="total_invoice_printing_type", columnDefinition = "bit default 0")
    private Boolean totalInvoicePrintingType;
    
    @Column(name="billing_printing_type", columnDefinition = "bit default 0")
    private Boolean billingPrintingType;
    
    @Column(name="property_printing_type", columnDefinition = "bit default 0")
    private Boolean propertyPrintingType;
    
    @Column(name="deposit_method1_reference", columnDefinition = "bigint")
    private Long depositMethod1reference;
    
    @Column(name="deposit_method1_split_payment", columnDefinition = "tinyint")
    private Byte depositMethod1SplitPayment;
    
    @Column(name="deposit_method1", columnDefinition = "tinyint")
    private Byte depositMethod1;
    
    @Column(name="planned_deposit_method1_payment_month_after", columnDefinition = "int")
    private Integer depositMethod1PlannedPaymentMonthAfter;
    
    @Column(name="planned_deposit_method1_plan_day", columnDefinition = "int")
    private Integer depositMethod1PlanDay;
    
    @Column(name="deposit_method1_div", columnDefinition = "nvarchar(30)")
    private String depositMethod1Division;
    
    @Column(name="deposit_method1_criteria_percentage", columnDefinition = "decimal(18,2)")
    private Double depositMethod1CriteriaPercentage;
    
    @Column(name="deposit_method1_div_standard_amount", columnDefinition = "decimal")
    private Double depositMethod1DivisionStandardAmount;
    
    @Column(name="deposit_method1_slip", columnDefinition = "tinyint")
    private Byte depositMethod1Slip;
    
    @Column(name="scheduled_deposit_method1_payment_month_after", columnDefinition = "int")
    private Integer depositMethod1ScheduledPaymentMonthAfter;
    
    @Column(name="scheduled_deposit_method1_plan_day", columnDefinition = "int")
    private Integer depositMethod1ScheduledDay;
    
    @Column(name="deposit_method2_reference", columnDefinition = "bigint")
    private Long depositMethod2Reference;
    
    @Column(name="deposit_method2_split_payment", columnDefinition = "tinyint")
    private Byte depositMethod2SplitPayment;
    
    @Column(name="deposit_method2", columnDefinition = "tinyint")
    private Byte depositMethod2;
    
    @Column(name="planned_deposit_method2_payment_month_after", columnDefinition = "int")
    private Integer depositMethod2PlannedPaymentMonthAfter;
    
    @Column(name="planned_deposit_method2_plan_day", columnDefinition = "int")
    private Integer depositMethod2PlanDay;
    
    @Column(name="deposit_method2_div", columnDefinition = "nvarchar(30)")
    private String depositMethod2Division;
    
    @Column(name="deposit_method2_criteria_percentage", columnDefinition = "decimal(18,2)")
    private Double depositMethod2CriteriaPercentage;
    
    @Column(name="deposit_method2_div_standard_amount", columnDefinition = "decimal")
    private Double depositMethod2DivisionStandardAmount;
    
    @Column(name="deposit_method2_slip", columnDefinition = "tinyint")
    private Byte depositMethod2Slip;
    
    @Column(name="scheduled_deposit_method2_payment_month_after", columnDefinition = "int")
    private Integer depositMethod2ScheduledPaymentMonthAfter;
    
    @Column(name="scheduled_deposit_method2_plan_day", columnDefinition = "int")
    private Integer depositMethod2ScheduledPlanDay;
    
    @Column(name="billing_cash_on_delivery", columnDefinition = "bit default 0")
    private Boolean cashOnDelivery;
    
    @Column(name="billing_collection", columnDefinition = "bit default 0")
    private Boolean collection;
    
    @Column(name="deposit_site", columnDefinition = "int")
    private Integer depositSite;
    
    @Column(name="shipment_after_payment_confirmed", columnDefinition = "bit default 0")
    private Boolean shipmentAfterPaymentConfirmed;
    
    @Column(name="billing_dedicated_slip", columnDefinition = "bit default 0")
    private Boolean dedicatedSlip;
    
    @Column(name="billing_payment_conditions", columnDefinition = "nvarchar(50)")
    private String paymentConditions;
    
	@Column(name="collect_safety_cooperation_fee_flg", columnDefinition = "bit default 0")
	private Boolean collectSafetyCooperationFeeFlag;

	@Column(name="collect_safety_cooperation_fee", columnDefinition = "decimal(18,2) default 0.00")
	private Double collectSafetyCooperationFee;
	
	@Column(name="collect_safety_cooperation_fee_memo", columnDefinition = "nvarchar(max)")
	private String collectSafetyCooperationFeeMemo;
	
	@Column(name="billing_memo", columnDefinition = "ntext")
    private String memo;

    @Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;

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
}