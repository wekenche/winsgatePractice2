/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.systems.Program;

/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_u_user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_u_user_id")
	private Integer id;
	
	@Column(name="mst_u_user_code", columnDefinition = "smallint", updatable = false)
	private Short code;
	
	@Column(name="mst_u_username", columnDefinition = "nvarchar(30)")
	private String username;
	
	@Column(name="username_kana", columnDefinition = "nvarchar(30)")
	private String usernameKana;
	
	@Column(name="password", columnDefinition = "nvarchar(100)")
	private String password;
	
	@Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isResigned;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_dept_id", insertable = true,nullable=true)
	private Department department;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name="created_by", updatable = false)
	private Integer createdBy;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program createdAt;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	private Program updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameKana() {
		return usernameKana;
	}

	public void setUsernameKana(String usernameKana) {
		this.usernameKana = usernameKana;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsResigned() {
		return isResigned;
	}

	public void setIsResigned(Boolean isResigned) {
		this.isResigned = isResigned;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Program getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Program updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}