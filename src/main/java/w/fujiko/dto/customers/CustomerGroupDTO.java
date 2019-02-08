/**
 * 
 */
package w.fujiko.dto.customers;

import java.io.Serializable;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;
import w.fujiko.dto.generalpurposes.items.GeneralPurposeItemDTO;
/**
 * 
 * @author yagami
 *
 */
public class CustomerGroupDTO implements Serializable {

	public CustomerGroupDTO(){}
	
	public CustomerGroupDTO(String code) {
		this.code = code;
	}

	private static final long serialVersionUID = 7257921460512063270L;
    
    private Integer id;
    
	@NotNull
    private String code;
    
    @NotNull
	private String name;
	  
    private String nameKana;

    private String shortName;
    
    private Boolean objectClass;

    @JsonProperty("generalPurposeItem")
	private GeneralPurposeItemDTO generalPurposeItem;

    private String rank;
    
    private String score1;
	
	@DateTimeFormat
    private Date score1Phase;

    private String score2;
	
	@DateTimeFormat
    private Date score2Phase;

    private String score3;
	
	@DateTimeFormat
    private Date score3Phase;

    private Long creditLimit;

	private String memo;
	
    private String companyInfoFile1;

    private String companyInfoFile2;

    private String companyInfoFile3;

    private String companyInfoFile4;

    private String companyInfoFile5;

    @JsonProperty("isEnd")
	private Boolean isEnd;

    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("dateUpdated")
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

	public GeneralPurposeItemDTO getGeneralPurposeItem() {
		return generalPurposeItem;
	}

	public void setGeneralPurposeItem(GeneralPurposeItemDTO generalPurposeItem) {
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