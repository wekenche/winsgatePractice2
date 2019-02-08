package w.fujiko.model.masters.products;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.infos.ProductFile;
import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.RatioClassification;
import w.fujiko.model.masters.Unit;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="mst_product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 7257921460512063270L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_product_id")
	private Integer id;
	
	@Column(name="product_cd", columnDefinition = "nvarchar(7)", unique=true, nullable = false, updatable = false)
	private String code;

	@Column(name="abolished_number_flg", columnDefinition = "bit default 0")
	private Boolean abolishedNumberFLG;
	
	@Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_mkr_id",insertable=true, updatable = true, nullable = false)
	private Maker maker;
	
	@Column(name="model_number", columnDefinition = "nvarchar(80)", nullable = false)
	private String modelNumber;
	
	@Column(name="product_name", columnDefinition = "nvarchar(40)", nullable = false)
	private String name;
	
	@Column(name="kana", columnDefinition = "nvarchar(20)")
	private String kana;
	
	@Column(name="instrument_body", columnDefinition = "nvarchar(40)")
	private String instrumentBody;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_u_prod_class_id",insertable=true, updatable = true)
	private ProductClassification productClassification;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_u_prod_class_item_id",insertable=true, updatable = true)
	private ProductClassificationItem productClassificationItem;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_ratio_class_id",insertable=true, updatable = true)
	private RatioClassification ratioClassification;
	
	@Column(name="lamp_flg", columnDefinition = "int")
    private Integer lampFLG;
	
	@Column(name="lamp_name", columnDefinition = "nvarchar(40)")
	private String lampName;
	
	@Column(name="ballast_flg", columnDefinition = "int")
    private Integer ballastFLG;
	
	@Column(name="ballast_name", columnDefinition = "nvarchar(40)")
	private String ballastName;
	
	@Column(name="transformer_flg", columnDefinition = "int")
    private Integer transformerFLG;
	
	@Column(name="transformer_name", columnDefinition = "nvarchar(40)")
	private String transformerName;
	
	@Column(name="special_order_classification", columnDefinition = "bit default 0")
	private Boolean specialOrderClassification;
	
	@Column(name="warranty_object_classification", columnDefinition = "bit default 0")
	private Boolean warrantyObjectClassification;
	
	@Column(name="complete_product", columnDefinition = "bit default 0")
	private Boolean completeProduct;
	
	@Column(name="not_subject_to_slip_printing", columnDefinition = "bit default 0")
	private Boolean notSubjectToSlipPrinting;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_unit_id",insertable=true, updatable = true)
	private Unit unit;
	
	@Column(name="purchasing_cost_date", columnDefinition = "datetime")	
	private Date purchasingCostDate;
	
	@Column(name="purchasing_cost_no_tax", columnDefinition = "decimal")
	private Double purchasingCostNoTax;
	
	@Column(name="inventory_unit_price_date", columnDefinition = "datetime")	
	private Date inventoryUnitPriceDate;
	
	@Column(name="inventory_unit_price_no_tax", columnDefinition = "decimal")
	private Double inventoryUnitPriceNoTax;
	
	@Column(name="last_unit_cost_per_unit_date", columnDefinition = "datetime")	
	private Date lastUnitCostPerUnitDate;
	
	@Column(name="last_unit_cost_per_unit_price", columnDefinition = "decimal")
	private Double lastUnitCostPerUnitPrice;
	
	@Column(name="list_price_date", columnDefinition = "datetime")	
	private Date listPriceDate;
	
	@Column(name="list_price_no_tax", columnDefinition = "decimal")
	private Double listPriceNoTax;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_general_purpose_item_id1", insertable=true, updatable = true)
	private GeneralPurposeItem genPurpItem1;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_general_purpose_item_id2", insertable=true, updatable = true)
	private GeneralPurposeItem genPurpItem2;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_general_purpose_item_id3", insertable=true, updatable = true)
	private GeneralPurposeItem genPurpItem3;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_general_purpose_item_id4", insertable=true, updatable = true)
	private GeneralPurposeItem genPurpItem4;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_general_purpose_item_id5", insertable=true, updatable = true)
	private GeneralPurposeItem genPurpItem5;
	
	@Column(name="notes", columnDefinition = "ntext")
	private String notes;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="product")
	private ProductFile file;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false, nullable = false)
	private User createdBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false, nullable = false)
	private Program createdAt;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

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

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
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

	public ProductClassification getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(ProductClassification productClassification) {
		this.productClassification = productClassification;
	}

	public ProductClassificationItem getProductClassificationItem() {
		return productClassificationItem;
	}

	public void setProductClassificationItem(ProductClassificationItem productClassificationItem) {
		this.productClassificationItem = productClassificationItem;
	}

	public RatioClassification getRatioClassification() {
		return ratioClassification;
	}

	public void setRatioClassification(RatioClassification ratioClassification) {
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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
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

	public GeneralPurposeItem getGenPurpItem1() {
		return genPurpItem1;
	}

	public void setGenPurpItem1(GeneralPurposeItem genPurpItem1) {
		this.genPurpItem1 = genPurpItem1;
	}

	public GeneralPurposeItem getGenPurpItem2() {
		return genPurpItem2;
	}

	public void setGenPurpItem2(GeneralPurposeItem genPurpItem2) {
		this.genPurpItem2 = genPurpItem2;
	}

	public GeneralPurposeItem getGenPurpItem3() {
		return genPurpItem3;
	}

	public void setGenPurpItem3(GeneralPurposeItem genPurpItem3) {
		this.genPurpItem3 = genPurpItem3;
	}

	public GeneralPurposeItem getGenPurpItem4() {
		return genPurpItem4;
	}

	public void setGenPurpItem4(GeneralPurposeItem genPurpItem4) {
		this.genPurpItem4 = genPurpItem4;
	}

	public GeneralPurposeItem getGenPurpItem5() {
		return genPurpItem5;
	}

	public void setGenPurpItem5(GeneralPurposeItem genPurpItem5) {
		this.genPurpItem5 = genPurpItem5;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ProductFile getFile() {
		return file;
	}

	public void setFile(ProductFile file) {
		this.file = file;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}

}