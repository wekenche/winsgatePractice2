package w.fujiko.dto.banks;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankBranchCreateDTO implements Serializable {

	private static final long serialVersionUID = -8361537823760462744L;
	
	private Integer id;
	
	@JsonProperty("bank")
	@NotNull
	private Integer bankId;
	
	@NotNull
	private Integer bankBranchCode;
	
	@NotNull
	private String bankBranchName;
	
	private String bankBranchKana;
	
	private Boolean isEnd;

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

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getBankBranchCode() {
		return bankBranchCode;
	}

	public void setBankBranchCode(Integer bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
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