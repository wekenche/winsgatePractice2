/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;

import javax.persistence. Column;
import javax.validation.constraints.NotNull;

import javax.persistence.Embeddable;

/**
 * @author yagami
 *
 */
@Embeddable
public class ProgramPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
		
	@NotNull
	@Column(name="mst_role_prog_id", columnDefinition="char(7)")
	private String program_id;

	@NotNull
	@Column(name="authorized_user_id")
	private Integer authorized_user;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorized_user == null) ? 0 : authorized_user.hashCode());
		result = prime * result + ((program_id == null) ? 0 : program_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgramPk other = (ProgramPk) obj;
		if (authorized_user == null) {
			if (other.authorized_user != null)
				return false;
		} else if (!authorized_user.equals(other.authorized_user))
			return false;
		if (program_id == null) {
			if (other.program_id != null)
				return false;
		} else if (!program_id.equals(other.program_id))
			return false;
		return true;
	}

	public String getProgram_id() {
		return program_id;
	}

	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}

	public Integer getAuthorized_user() {
		return authorized_user;
	}

	public void setAuthorized_user(Integer authorized_user) {
		this.authorized_user = authorized_user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}