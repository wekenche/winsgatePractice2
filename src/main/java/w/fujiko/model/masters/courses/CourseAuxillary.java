package w.fujiko.model.masters.courses;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="mst_u_course_auxillary")
public class CourseAuxillary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1223514064914884665L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_u_course_aux_id")
	private Integer id;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_u_course_id", insertable=true, updatable = true)
	private Course course;
	
	@Column(name="mst_u_course_aux_cd", columnDefinition = "int", updatable = false)
	private Integer code;
	
	@Column(name="name", columnDefinition = "nvarchar(20)")
	private String name;
	
	@Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User createdBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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
