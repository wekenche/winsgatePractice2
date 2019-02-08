package w.fujiko.dto.products;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCreateDTO implements Serializable {

	private static final long serialVersionUID = -2708255446047643856L;
	
	private Integer id;
	
	private String code;

	private Boolean abolishedNumberFLG;
	
	private Boolean isEnd;
	
	@JsonProperty("makerId")
	@NotNull
	private Integer makerId;
	
	@NotNull
	private String modelNumber;
	
	@NotNull
	private String name;
	
	private String kana;
	
	private String instrumentBody;
	
	@JsonProperty("productClassificationId")
	private Integer productClassificationId;
	
	@JsonProperty("productClassificationItemId")
	private Integer productClassificationItemId;
	
	@JsonProperty("ratioClassificationId")
	private Integer ratioClassificationId;
	
	private Integer lampFLG;
	
	private String lampName;
	
	private Integer ballastFLG;
	
	private String ballastName;
	
	private Integer transformerFLG;
	
	private String transformerName;
	
	private Boolean specialOrderClassification;
	
	private Boolean warrantyObjectClassification;
	
	private Boolean completeProduct;
	
	private Boolean notSubjectToSlipPrinting;
	
	@JsonProperty("unitId")
	private Integer unitId;
	
	private Date purchasingCostDate;
	
	private Double purchasingCostNoTax;
	
	private Date inventoryUnitPriceDate;
	
	private Double inventoryUnitPriceNoTax;
	
	private Date lastUnitCostPerUnitDate;
	
	private Double lastUnitCostPerUnitPrice;
	
	private Date listPriceDate;
	
	private Double listPriceNoTax;
	
	@JsonProperty("genPurpItem1Id")
	private Integer genPurpItem1Id;
	
	@JsonProperty("genPurpItem2Id")
	private Integer genPurpItem2Id;
	
	@JsonProperty("genPurpItem3Id")
	private Integer genPurpItem3Id;
	
	@JsonProperty("genPurpItem4Id")
	private Integer genPurpItem4Id;
	
	@JsonProperty("genPurpItem5Id")
	private Integer genPurpItem5Id;
	
	private String notes;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getAbolishedNumberFLG() {
		return abolishedNumberFLG;
	}

	public void setAbolishedNumberFLG(Boolean abolishedNumberFLG) {
		this.abolishedNumberFLG = abolishedNumberFLG;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getInstrumentBody() {
		return instrumentBody;
	}

	public void setInstrumentBody(String instrumentBody) {
		this.instrumentBody = instrumentBody;
	}

	public Integer getProductClassificationId() {
		return productClassificationId;
	}

	public void setProductClassificationId(Integer productClassificationId) {
		this.productClassificationId = productClassificationId;
	}

	public Integer getProductClassificationItemId() {
		return productClassificationItemId;
	}

	public void setProductClassificationItemId(Integer productClassificationItemId) {
		this.productClassificationItemId = productClassificationItemId;
	}

	public Integer getRatioClassificationId() {
		return ratioClassificationId;
	}

	public void setRatioClassificationId(Integer ratioClassificationId) {
		this.ratioClassificationId = ratioClassificationId;
	}

	public Integer getLampFLG() {
		return lampFLG;
	}

	public void setLampFLG(Integer lampFLG) {
		this.lampFLG = lampFLG;
	}

	public String getLampName() {
		return lampName;
	}

	public void setLampName(String lampName) {
		this.lampName = lampName;
	}

	public Integer getBallastFLG() {
		return ballastFLG;
	}

	public void setBallastFLG(Integer ballastFLG) {
		this.ballastFLG = ballastFLG;
	}

	public String getBallastName() {
		return ballastName;
	}

	public void setBallastName(String ballastName) {
		this.ballastName = ballastName;
	}

	public Integer getTransformerFLG() {
		return transformerFLG;
	}

	public void setTransformerFLG(Integer transformerFLG) {
		this.transformerFLG = transformerFLG;
	}

	public String getTransformerName() {
		return transformerName;
	}

	public void setTransformerName(String transformerName) {
		this.transformerName = transformerName;
	}

	public Boolean getSpecialOrderClassification() {
		return specialOrderClassification;
	}

	public void setSpecialOrderClassification(Boolean specialOrderClassification) {
		this.specialOrderClassification = specialOrderClassification;
	}

	public Boolean getWarrantyObjectClassification() {
		return warrantyObjectClassification;
	}

	public void setWarrantyObjectClassification(Boolean warrantyObjectClassification) {
		this.warrantyObjectClassification = warrantyObjectClassification;
	}

	public Boolean getCompleteProduct() {
		return completeProduct;
	}

	public void setCompleteProduct(Boolean completeProduct) {
		this.completeProduct = completeProduct;
	}

	public Boolean getNotSubjectToSlipPrinting() {
		return notSubjectToSlipPrinting;
	}

	public void setNotSubjectToSlipPrinting(Boolean notSubjectToSlipPrinting) {
		this.notSubjectToSlipPrinting = notSubjectToSlipPrinting;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Date getPurchasingCostDate() {
		return purchasingCostDate;
	}

	public void setPurchasingCostDate(Date purchasingCostDate) {
		this.purchasingCostDate = purchasingCostDate;
	}

	public Double getPurchasingCostNoTax() {
		return purchasingCostNoTax;
	}

	public void setPurchasingCostNoTax(Double purchasingCostNoTax) {
		this.purchasingCostNoTax = purchasingCostNoTax;
	}

	public Date getInventoryUnitPriceDate() {
		return inventoryUnitPriceDate;
	}

	public void setInventoryUnitPriceDate(Date inventoryUnitPriceDate) {
		this.inventoryUnitPriceDate = inventoryUnitPriceDate;
	}

	public Double getInventoryUnitPriceNoTax() {
		return inventoryUnitPriceNoTax;
	}

	public void setInventoryUnitPriceNoTax(Double inventoryUnitPriceNoTax) {
		this.inventoryUnitPriceNoTax = inventoryUnitPriceNoTax;
	}

	public Date getLastUnitCostPerUnitDate() {
		return lastUnitCostPerUnitDate;
	}

	public void setLastUnitCostPerUnitDate(Date lastUnitCostPerUnitDate) {
		this.lastUnitCostPerUnitDate = lastUnitCostPerUnitDate;
	}

	public Double getLastUnitCostPerUnitPrice() {
		return lastUnitCostPerUnitPrice;
	}

	public void setLastUnitCostPerUnitPrice(Double lastUnitCostPerUnitPrice) {
		this.lastUnitCostPerUnitPrice = lastUnitCostPerUnitPrice;
	}

	public Date getListPriceDate() {
		return listPriceDate;
	}

	public void setListPriceDate(Date listPriceDate) {
		this.listPriceDate = listPriceDate;
	}

	public Double getListPriceNoTax() {
		return listPriceNoTax;
	}

	public void setListPriceNoTax(Double listPriceNoTax) {
		this.listPriceNoTax = listPriceNoTax;
	}

	public Integer getGenPurpItem1Id() {
		return genPurpItem1Id;
	}

	public void setGenPurpItem1Id(Integer genPurpItem1Id) {
		this.genPurpItem1Id = genPurpItem1Id;
	}

	public Integer getGenPurpItem2Id() {
		return genPurpItem2Id;
	}

	public void setGenPurpItem2Id(Integer genPurpItem2Id) {
		this.genPurpItem2Id = genPurpItem2Id;
	}

	public Integer getGenPurpItem3Id() {
		return genPurpItem3Id;
	}

	public void setGenPurpItem3Id(Integer genPurpItem3Id) {
		this.genPurpItem3Id = genPurpItem3Id;
	}

	public Integer getGenPurpItem4Id() {
		return genPurpItem4Id;
	}

	public void setGenPurpItem4Id(Integer genPurpItem4Id) {
		this.genPurpItem4Id = genPurpItem4Id;
	}

	public Integer getGenPurpItem5Id() {
		return genPurpItem5Id;
	}

	public void setGenPurpItem5Id(Integer genPurpItem5Id) {
		this.genPurpItem5Id = genPurpItem5Id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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