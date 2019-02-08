/**
 * 
 */
package w.fujiko.dto.customers;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
/**
 * 
 * @author yagami
 *
 */
public class CustomerGroupDefaultDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;
    
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