package w.fujiko.dto.destinations;

import java.io.Serializable;

public class DestinationDefaultDTO implements Serializable {

	private static final long serialVersionUID = -2402451169666717025L;
	
	private Integer id;

	private Integer code;
	
	private String name1;

	private String name2;

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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

}