/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import w.fujiko.model.masters.systems.Program;
/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_role_prog")
public class RoleProgram implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@EmbeddedId
	private ProgramPk mstRoleProg;

	@ManyToOne
	@JoinColumn(name="mst_role_prog_id", insertable = false, updatable = false)
	@JsonBackReference(value="roleProgram")
	private Program program;

	@ManyToOne
	@JoinColumn(name="authorized_user_id", insertable = false, updatable = false)
	@JsonBackReference(value="authorizedUser")
	private User authorizedUser;

	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="role_program",orphanRemoval=true)	
	@JsonManagedReference(value="roleProgram")
	private Set<RoleProgramCommand> programCommands = new HashSet<>();

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="roleProgram",orphanRemoval=true)
	@JsonManagedReference
	private Set<MyMenu> myMenus=new HashSet<>();

	@NotNull
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = true)
	@JsonBackReference(value="creatorUser")
	@NotNull
	private User createdBy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = true)
	@NotNull
	@JsonBackReference(value="creatorProgram")
	private Program createdAt;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	@NotNull
	@JsonBackReference(value="updatorUser")
	private User updatedBy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	@NotNull
	@JsonBackReference(value="updatorProgram")
	private Program updatedAt;

	public ProgramPk getMstRoleProg() {
		return mstRoleProg;
	}

	public void setMstRoleProg(ProgramPk mstRoleProg) {
		this.mstRoleProg = mstRoleProg;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	public void setAuthorizedUser(User authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	public Set<RoleProgramCommand> getProgramCommands() {
		return programCommands;
	}

	public void setProgramCommands(Set<RoleProgramCommand> programCommands) {
		this.programCommands = programCommands;
	}

	public Set<MyMenu> getMyMenus() {
		return myMenus;
	}

	public void setMyMenus(Set<MyMenu> myMenus) {
		this.myMenus = myMenus;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}	
}