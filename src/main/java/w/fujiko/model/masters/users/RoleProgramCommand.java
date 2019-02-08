/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCommand;

/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_role_prog_command")
public class RoleProgramCommand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

	@EmbeddedId
	private RoleProgramCommandPk command;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="mst_role_prog_command_progid",referencedColumnName="mst_role_prog_id",insertable=false, updatable = false),
		@JoinColumn(name="authorized_user_id",referencedColumnName="authorized_user_id",insertable=false, updatable = false)
	})
	@JsonBackReference(value="roleProgram")
	private RoleProgram role_program;
	
	@ManyToOne
	@JoinColumn(name="mst_role_prog_command_id",insertable=false, updatable = false)
	@JsonBackReference(value="programCommand")	
	private ProgramCommand program_command;

	//@NotNull
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = true, updatable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = true)
	@NotNull
	private User created_by;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = true)
	@JsonBackReference(value = "created_at")
	@NotNull
	private Program created_at;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)	
	private User updated_by;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	@JsonBackReference(value = "updated_at")
	private Program updated_at;

	public RoleProgramCommandPk getCommand() {
		return command;
	}

	public void setCommand(RoleProgramCommandPk command) {
		this.command = command;
	}

	public RoleProgram getRole_program() {
		return role_program;
	}

	public void setRole_program(RoleProgram role_program) {
		this.role_program = role_program;
	}

	public ProgramCommand getProgram_command() {
		return program_command;
	}

	public void setProgram_command(ProgramCommand program_command) {
		this.program_command = program_command;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public Program getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Program created_at) {
		this.created_at = created_at;
	}

	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}

	public User getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(User updated_by) {
		this.updated_by = updated_by;
	}

	public Program getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Program updated_at) {
		this.updated_at = updated_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}