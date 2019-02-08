package w.fujiko.dto.suppliers;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierGroupUpdateDTO implements Serializable {

	private static final long serialVersionUID = 3102931534396331414L;
	
	private Integer id;
    
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

    @JsonProperty("dateUpdated")
    @JsonIgnore
    private Date dateUpdated=new Date();

    @JsonProperty("updatedBy")
    @NotNull
    private Integer updatedById;

    @JsonProperty("updatedAt")
    @NotNull
    private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}

}