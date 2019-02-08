package w.fujiko.dto.products;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductClassificationItemUpdateDTO implements Serializable {
	
	private static final long serialVersionUID = 7631099381997951685L;
	
	@NotNull
	private Integer id;
	
	@JsonProperty("productClassification")
	@NotNull
	private Integer productClassificationId;
	
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

	public Integer getProductClassificationId() {
		return productClassificationId;
	}

	public void setProductClassificationId(Integer productClassificationId) {
		this.productClassificationId = productClassificationId;
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