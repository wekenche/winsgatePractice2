package w.fujiko.dto.suppliers;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SupplierGroupDefaultDTO implements Serializable {

	private static final long serialVersionUID = 6754745018137179196L;
	
	private Integer id;

	@NotNull
	private String code;

	@NotNull
	private String name;

	private String nameKana;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

}