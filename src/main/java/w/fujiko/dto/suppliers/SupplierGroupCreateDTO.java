package w.fujiko.dto.suppliers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierGroupCreateDTO implements Serializable {

	private static final long serialVersionUID = -6709438820284993627L;
	
	@NotNull
    private String code;
    
    @NotNull
	private String name;
	  
    private String nameKana;

    private String shortName;
    
    private Boolean objectClass;

    @JsonProperty("generalPurposeItemId")
	private Integer generalPurposeItemId;

    private String rank;
	
    private String score1;

    private String score2;

    private String score3;

    private Long creditLimit;

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

	public Integer getGeneralPurposeItemId() {
		return generalPurposeItemId;
	}

	public void setGeneralPurposeItemId(Integer generalPurposeItemId) {
		this.generalPurposeItemId = generalPurposeItemId;
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

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}

	public String getScore3() {
		return score3;
	}

	public void setScore3(String score3) {
		this.score3 = score3;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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