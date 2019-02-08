/**
 * 
 */
package w.fujiko.dto.programs.commands;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class ProgramCommandDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("keyName")
	private String keyName;

    @JsonProperty("keyCode")
	private Integer keyCode;

	@JsonProperty("commandFunction")
	private String commandFunction;

	@JsonProperty("isActive")
	private boolean isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

    public Integer getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(Integer keyCode) {
		this.keyCode = keyCode;
	}
	
	public String getCommandFunction() {
		return commandFunction;
	}

	public void setCommandFunction(String commandFunction) {
		this.commandFunction = commandFunction;
	}

	public boolean getIsActive() {
		return true;
	}

}
