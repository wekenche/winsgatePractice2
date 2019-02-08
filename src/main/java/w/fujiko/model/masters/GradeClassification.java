package w.fujiko.model.masters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.Maker;

@Entity
@Table(name="mst_grade_classification")
public class GradeClassification  implements Serializable {

	private static final long serialVersionUID = 9194748554293489630L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_grade_classification_id",columnDefinition="smallint")
	private Short id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@NotNull
	@JoinColumn(name="mst_maker_id",insertable=true,updatable=true,nullable=false)
	private Maker maker;
    
	@NotNull
	@Column(name="mst_grade_classification_cd", columnDefinition="smallint",nullable=false, updatable = false)
	private short code;
	
	@NotNull
	@Column(name="name", columnDefinition="nvarchar(25)",nullable=false)
	private String name;
	
	@NotNull
	@Column(name="is_end", columnDefinition = "bit default 0",nullable=false)
	private Boolean isEnd;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false,nullable=false)
	@NotNull
	private User createdBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false,nullable=false)
	@NotNull
	private Program createdAt;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	private User updatedBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
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