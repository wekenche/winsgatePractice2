package w.fujiko.dto.units;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitUpdateDTO implements Serializable {

	private static final long serialVersionUID = -5428690213641924864L;
	
	@NotNull
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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