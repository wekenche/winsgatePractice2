package w.fujiko.model.masters.suppliers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fte.api.Defaults;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;

@Entity
@Table(name="mst_splr_group")
public class SupplierGroup extends Defaults implements Serializable {

	private static final long serialVersionUID = 5895922605599909275L;
	
	@NotNull
    @Column(name="mst_splr_group_cd", columnDefinition = "varchar(6)", updatable = false)
    private String code;
    
    @NotNull
    @Column(name="name", columnDefinition = "nvarchar(100)")
    private String name;

    @Column(name="kana", columnDefinition = "nvarchar(50)")
    private String nameKana;

    @Column(name="short_name", columnDefinition = "nvarchar(25)")
    private String shortName;
    
    @Column(name="obj_class", columnDefinition = "bit default 0")
    private Boolean objectClass;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="mst_general_purpose_id", columnDefinition = "int", insertable=true,updatable=true)
    private GeneralPurposeItem generalPurposeItem;

    @Column(name="rank", columnDefinition = "varchar(2)")
    private String rank;
    
    @Column(name="score_1", columnDefinition = "varchar(2)")
    private String score1;
    
    @Column(name="score_1_phase", columnDefinition = "date", nullable=true)
    private Date score1Phase;

    @Column(name="score_2", columnDefinition = "varchar(2)")
    private String score2;
    
    @Column(name="score_2_phase", columnDefinition = "date", nullable=true)
    private Date score2Phase;

    @Column(name="score_3", columnDefinition = "varchar(2)")
    private String score3;
    
    @Column(name="score_3_phase",columnDefinition = "date", nullable=true)
    private Date score3Phase;

    @Column(name="credit_limit", columnDefinition = "bigint")
    private Long creditLimit;

    @Column(name="memo", columnDefinition = "nvarchar(30)")
    private String memo;

    @Column(name="is_end", columnDefinition = "bit default 0")
    private Boolean isEnd;
    
    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="group")
	@JsonManagedReference
    private Set<SupplierBase> supplierBases=new HashSet<>();

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

	public Boolean getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(Boolean objectClass) {
		this.objectClass = objectClass;
	}

	public GeneralPurposeItem getGeneralPurposeItem() {
		return generalPurposeItem;
	}

	public void setGeneralPurposeItem(GeneralPurposeItem generalPurposeItem) {
		this.generalPurposeItem = generalPurposeItem;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getScore1() {
		return score1;
	}

	public void setScore1(String score1) {
		this.score1 = score1;
	}

	public Date getScore1Phase() {
		return score1Phase;
	}

	public void setScore1Phase(Date score1Phase) {
		this.score1Phase = score1Phase;
	}

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}

	public Date getScore2Phase() {
		return score2Phase;
	}

	public void setScore2Phase(Date score2Phase) {
		this.score2Phase = score2Phase;
	}

	public String getScore3() {
		return score3;
	}

	public void setScore3(String score3) {
		this.score3 = score3;
	}

	public Date getScore3Phase() {
		return score3Phase;
	}

	public void setScore3Phase(Date score3Phase) {
		this.score3Phase = score3Phase;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
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

	public Set<SupplierBase> getSupplierBases() {
		return supplierBases;
	}

	public void setSupplierBases(Set<SupplierBase> supplierBases) {
		this.supplierBases = supplierBases;
	}

}