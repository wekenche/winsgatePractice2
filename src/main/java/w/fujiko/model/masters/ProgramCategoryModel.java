package w.fujiko.model.masters;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgramCategoryModel implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
	
	@JsonProperty("category_id")
	private String categoryId;
	
	@JsonProperty("category_name")
	private String categoryName;
	
	private List<ProgramModel> programs;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ProgramModel> getPrograms() {
		return programs;
	}

	public void setPrograms(List<ProgramModel> programs) {
		this.programs = programs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
			
}