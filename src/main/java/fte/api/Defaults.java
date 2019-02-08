 /**
 * 
 */
package fte.api;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

/**
 * @author Try-Parser 
 *
 */
@MappedSuperclass
public abstract class Defaults implements Serializable {
	private static final long serialVersionUID = 9098563142871729601L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", updatable = false, nullable = false)
	private Integer id;

	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@JsonBackReference(value = "created_by")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User createdBy;

	@JsonBackReference(value = "updated_by")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program createdAt;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Program getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Program createdAt) {
		this.createdAt = createdAt;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}

}
