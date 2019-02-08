/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.systems.Program;
/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_mymenu")
public class MyMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_mymenu_id",columnDefinition = "int")
	private Integer id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="mst_mymenu_prog_id",referencedColumnName="mst_role_prog_id"),
		@JoinColumn(name="authorized_user_id",referencedColumnName="authorized_user_id")
	})
	@JsonBackReference
	private RoleProgram roleProgram;

	@NotNull
	@Column(name="menu_sequence", columnDefinition = "int")
	private Integer menuSequence;

	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	@JsonBackReference(value = "createdBy")
	@NotNull
	private User createdBy;

	@ManyToOne
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	@JsonBackReference(value = "createdAt")
	@NotNull
	private Program createdAt;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;

	@ManyToOne
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	@JsonBackReference(value = "updatedBy")
	private User updatedBy;

	@ManyToOne
	@JoinColumn(name="updated_at", insertable=true, updatable = true)	
	@JsonBackReference(value = "updatedAt")
	private Program updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleProgram getRoleProgram() {
		return roleProgram;
	}

	public void setRoleProgram(RoleProgram roleProgram) {
		this.roleProgram = roleProgram;
	}

	public Integer getMenuSequence() {
		return menuSequence;
	}

	public void setMenuSequence(Integer menuSequence) {
		this.menuSequence = menuSequence;
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