package w.fujiko.dto.products;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductClassificationItemCreateDTO implements Serializable {

	private static final long serialVersionUID = 7464109923128058950L;
	
	private Integer id;
	
	@JsonProperty("productClassification")
	@NotNull
	private Integer productClassificationId;
	
	@NotNull
	private Integer code;
	
	@NotNull
	private String name;
	
	@NotNull
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

	public Integer getProductClassificationId() {
		return productClassificationId;
	}

	public void setProductClassificationId(Integer productClassificationId) {
		this.productClassificationId = productClassificationId;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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