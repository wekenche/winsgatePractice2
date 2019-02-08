/**
 * 
 */
package w.fujiko.model.masters.systems;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fte.api.Defaults;

/**
 * @author Try-Parser
 *
 */
@Entity
@Table(name="sys_log")
public class SystemLogs extends Defaults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3335928002277824215L;
	
	@Column
	private String ip;
	
	@Column
	private String iss;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="program_id")
	private Program program_id;
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getIss() {
		return iss;
	}
	
	public void setIss(String iss) {
		this.iss = iss;
	}

	public Program getProgram_id() {
		return program_id;
	}

	public void setProgram_id(Program program_id) {
		this.program_id = program_id;
	}
}
