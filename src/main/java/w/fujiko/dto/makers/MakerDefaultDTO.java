package w.fujiko.dto.makers;

import java.io.Serializable;

public class MakerDefaultDTO implements Serializable {

	private static final long serialVersionUID = -1729484593930770855L;
	
	private Integer id;
	
	private Integer code;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}