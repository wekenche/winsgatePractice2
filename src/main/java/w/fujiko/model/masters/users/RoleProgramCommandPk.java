/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.Embeddable;


/**
 * @author yagami
 *
 */
@Embeddable
public class RoleProgramCommandPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

	@Column(name="authorized_user_id",columnDefinition="int")
	@NotNull
	private Integer user_id;

	@Column(name="mst_role_prog_command_progid",columnDefinition="char(7)")
	@NotNull
	private String program_id;

	@Column(name="mst_role_prog_command_id",columnDefinition="int")	
	@NotNull
	private Integer command_id;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getProgram_id() {
		return program_id;
	}

	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}

	public Integer getCommand_id() {
		return command_id;
	}

	public void setCommand_id(Integer command_id) {
		this.command_id = command_id;
	}
}