package w.fujiko.model.masters;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author festadillo
 *
 */
public class ProgramModel implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
	
	@JsonProperty("program_id")
	private String programId;
	
	@JsonProperty("menu_sequence")
	private int menuSuence;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("text")
	private String text;

	@JsonIgnore
	private String categoryId;

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public int getMenuSuence() {
		return menuSuence;
	}

	public void setMenuSuence(int menuSuence) {
		this.menuSuence = menuSuence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
					
}