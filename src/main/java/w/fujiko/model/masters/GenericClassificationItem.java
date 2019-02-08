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

import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

@Entity
@Table(name="mst_generic_classification_item")
public class GenericClassificationItem implements Serializable {

	private static final long serialVersionUID = -3339941330500862402L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_generic_class_item_id")
	private Integer id;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_generic_class_id", insertable=true, updatable = true)
	private GenericClassification genericClassification;
	
	@NotNull
	@Column(name="generic_class_item_cd", columnDefinition="smallint", updatable = false)
	private int code;
	
	@NotNull
	@Column(name="setting_character", columnDefinition="nvarchar(25)")
	private String settingCharacter;
	
	@NotNull
	@Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isEnd;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	@NotNull
	private User createdBy;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GenericClassification getGenericClassification() {
		return genericClassification;
	}

	public void setGenericClassification(GenericClassification genericClassification) {
		this.genericClassification = genericClassification;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSettingCharacter() {
		return settingCharacter;
	}

	public void setSettingCharacter(String settingCharacter) {
		this.settingCharacter = settingCharacter;
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