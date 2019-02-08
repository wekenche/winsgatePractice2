package w.fujiko.dto.banks;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankBranchUpdateDTO implements Serializable {

	private static final long serialVersionUID = 7982296831909644316L;
	
	@NotNull
	private Integer id;
	
	@JsonProperty("bank")
	@NotNull
	private Integer bankId;
	
	@NotNull
	private String bankBranchName;
	
	private String bankBranchKana;
	
	private Boolean isEnd;

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

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBankBranchKana() {
		return bankBranchKana;
	}

	public void setBankBranchKana(String bankBranchKana) {
		this.bankBranchKana = bankBranchKana;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
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