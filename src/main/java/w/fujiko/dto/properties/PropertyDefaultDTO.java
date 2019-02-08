package w.fujiko.dto.properties;

import java.io.Serializable;

public class PropertyDefaultDTO implements Serializable {

	private static final long serialVersionUID = -3694401684135232199L;
	
	private Integer id;
	
	private Integer propertyNo;
	
	private String propertyName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(Integer propertyNo) {
		this.propertyNo = propertyNo;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}