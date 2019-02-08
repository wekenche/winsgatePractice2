/**
 * 
 */
package w.fujiko.dto.customers.departments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class CustomerDepartmentDefaultDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @JsonProperty("id")
    private int id;
    
    @NotNull
    @JsonProperty("code")
    private int code;
    
    @NotNull
    @JsonProperty("name")
    private String name;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
    }
    
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}