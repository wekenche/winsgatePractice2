package w.fujiko.dto.products;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.generalpurposes.items.GeneralPurposeItemDTO;
import w.fujiko.dto.makers.MakerDTO;
import w.fujiko.dto.ratioclassifications.RatioClassificationDTO;
import w.fujiko.dto.units.UnitDTO;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 6882267901565153576L;

	private Integer id;
	
	private String code;

	private Boolean abolishedNumberFLG;
	
	private Boolean isEnd;
	
	private MakerDTO maker;
	
	private String modelNumber;
	
	private String name;
	
	private String kana;
	
	private String instrumentBody;
	
	private ProductClassificationDTO productClassification;
	
	private ProductClassificationItemDTO productClassificationItem;
	
	private RatioClassificationDTO ratioClassification;
	
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
	
	private UnitDTO unit;
	
	private Date purchasingCostDate;
	
	private Double purchasingCostNoTax;
		
	private Date inventoryUnitPriceDate;
	
	private Double inventoryUnitPriceNoTax;
	
	private Date lastUnitCostPerUnitDate;
	
	private Double lastUnitCostPerUnitPrice;
	
	private Date listPriceDate;
	
	private Double listPriceNoTax;
	
	private GeneralPurposeItemDTO genPurpItem1;
	
	private GeneralPurposeItemDTO genPurpItem2;
	
	private GeneralPurposeItemDTO genPurpItem3;
	
	private GeneralPurposeItemDTO genPurpItem4;
	
	private GeneralPurposeItemDTO genPurpItem5;
	
	@JsonProperty("smallSetFilename")
	private String fileSmallSetFilename;

	@JsonProperty("drawingSpecFilename")
	private String fileDrawingSpecFilename;

	@JsonProperty("instructionManualFilename")
	private String fileInstructionManualFilename;

	private String notes;

	private Date dateCreated;

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

	public MakerDTO getMaker() {
		return maker;
	}

	public void setMaker(MakerDTO maker) {
		this.maker = maker;
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

	public ProductClassificationDTO getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(ProductClassificationDTO productClassification) {
		this.productClassification = productClassification;
	}

	public ProductClassificationItemDTO getProductClassificationItem() {
		return productClassificationItem;
	}

	public void setProductClassificationItem(ProductClassificationItemDTO productClassificationItem) {
		this.productClassificationItem = productClassificationItem;
	}

	public RatioClassificationDTO getRatioClassification() {
		return ratioClassification;
	}

	public void setRatioClassification(RatioClassificationDTO ratioClassification) {
		this.ratioClassification = ratioClassification;
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

	public UnitDTO getUnit() {
		return unit;
	}

	public void setUnit(UnitDTO unit) {
		this.unit = unit;
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

	public GeneralPurposeItemDTO getGenPurpItem1() {
		return genPurpItem1;
	}

	public void setGenPurpItem1(GeneralPurposeItemDTO genPurpItem1) {
		this.genPurpItem1 = genPurpItem1;
	}

	public GeneralPurposeItemDTO getGenPurpItem2() {
		return genPurpItem2;
	}

	public void setGenPurpItem2(GeneralPurposeItemDTO genPurpItem2) {
		this.genPurpItem2 = genPurpItem2;
	}

	public GeneralPurposeItemDTO getGenPurpItem3() {
		return genPurpItem3;
	}

	public void setGenPurpItem3(GeneralPurposeItemDTO genPurpItem3) {
		this.genPurpItem3 = genPurpItem3;
	}

	public GeneralPurposeItemDTO getGenPurpItem4() {
		return genPurpItem4;
	}

	public void setGenPurpItem4(GeneralPurposeItemDTO genPurpItem4) {
		this.genPurpItem4 = genPurpItem4;
	}

	public GeneralPurposeItemDTO getGenPurpItem5() {
		return genPurpItem5;
	}

	public void setGenPurpItem5(GeneralPurposeItemDTO genPurpItem5) {
		this.genPurpItem5 = genPurpItem5;
	}

	public String getFileSmallSetFilename() {
		return fileSmallSetFilename;
	}

	public void setFileSmallSetFilename(String fileSmallSetFilename) {
		this.fileSmallSetFilename = fileSmallSetFilename;
	}

	public String getFileDrawingSpecFilename() {
		return fileDrawingSpecFilename;
	}

	public void setFileDrawingSpecFilename(String fileDrawingSpecFilename) {
		this.fileDrawingSpecFilename = fileDrawingSpecFilename;
	}

	public String getFileInstructionManualFilename() {
		return fileInstructionManualFilename;
	}

	public void setFileInstructionManualFilename(String fileInstructionManualFilename) {
		this.fileInstructionManualFilename = fileInstructionManualFilename;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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