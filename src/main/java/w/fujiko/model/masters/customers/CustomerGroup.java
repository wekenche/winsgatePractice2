/**
 * 
 */
package w.fujiko.model.masters.customers;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;
import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_cst_group")
public class CustomerGroup extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@NotNull
    @Column(name="mst_cst_group_cd", columnDefinition = "varchar(6)", updatable = false)
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

    @Column(name="company_info_file_1", columnDefinition = "nvarchar(100)")
    private String companyInfoFile1;

    @Column(name="company_info_file_2", columnDefinition = "nvarchar(100)")
    private String companyInfoFile2;

    @Column(name="company_info_file_3", columnDefinition = "nvarchar(100)")
    private String companyInfoFile3;

    @Column(name="company_info_file_4", columnDefinition = "nvarchar(100)")
    private String companyInfoFile4;

    @Column(name="company_info_file_5", columnDefinition = "nvarchar(100)")
    private String companyInfoFile5;

    @Column(name="is_end", columnDefinition = "bit default 0")
    private Boolean isEnd;

    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="group")
	@JsonManagedReference
    private Set<CustomerBase> customerBases=new HashSet<>();
    
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

    public String getCompanyInfoFile1() {
        return companyInfoFile1;
    }

    public void setCompanyInfoFile1(String companyInfoFile1) {
        this.companyInfoFile1 = companyInfoFile1;
    }

    public String getCompanyInfoFile2() {
        return companyInfoFile2;
    }

    public void setCompanyInfoFile2(String companyInfoFile2) {
        this.companyInfoFile2 = companyInfoFile2;
    }

    public String getCompanyInfoFile3() {
        return companyInfoFile3;
    }

    public void setCompanyInfoFile3(String companyInfoFile3) {
        this.companyInfoFile3 = companyInfoFile3;
    }

    public String getCompanyInfoFile4() {
        return companyInfoFile4;
    }

    public void setCompanyInfoFile4(String companyInfoFile4) {
        this.companyInfoFile4 = companyInfoFile4;
    }

    public String getCompanyInfoFile5() {
        return companyInfoFile5;
    }

    public void setCompanyInfoFile5(String companyInfoFile5) {
        this.companyInfoFile5 = companyInfoFile5;
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }

    public Set<CustomerBase> getCustomerBases() {
        return customerBases;
    }

    public void setCustomerBases(Set<CustomerBase> customerBases) {
        this.customerBases = customerBases;
    }
}