package w.fujiko.dto.banks;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankCreateDTO implements Serializable {

	private static final long serialVersionUID = 2370840471343171564L;
	
	private Integer id;
	
	@NotNull
	private Integer bankCode;
	
	@NotNull
	private String bankName;
	
	private String bankKana;
	
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

	public Integer getBankCode() {
		return bankCode;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankKana() {
		return bankKana;
	}

	public void setBankKana(String bankKana) {
		this.bankKana = bankKana;
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