package w.fujiko.dto.products;

import java.io.Serializable;

import w.fujiko.dto.units.UnitDefaultDTO;

public class ProductDefaultDTO implements Serializable {

	private static final long serialVersionUID = 6882267901565153576L;

	private Integer id;
	
    private String code;
    
	private String name;
	
	private UnitDefaultDTO unit;

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
	
	public UnitDefaultDTO getUnit() {
		return unit;
	}

	public void setUnit(UnitDefaultDTO unit) {
		this.unit = unit;
	}
}