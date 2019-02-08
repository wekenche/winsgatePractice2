package w.fujiko.dto.units;

import java.io.Serializable;

public class UnitDefaultDTO implements Serializable {

	private static final long serialVersionUID = 5869164411329609367L;
	
	private Integer id;
	
	private Integer code;
	
	private String name;
	
	private Boolean isEnd;


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

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

}