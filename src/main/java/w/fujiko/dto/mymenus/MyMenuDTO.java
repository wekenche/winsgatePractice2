/**
 * 
 */
package w.fujiko.dto.mymenus;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.roleprograms.RoleProgramDefaultDTO;

/**
 * 
 * @author yagami
 *
 */
public class MyMenuDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    private Integer id;
    
    @JsonProperty("roleProgram")
	private RoleProgramDefaultDTO roleProgram;

    @JsonProperty("menuSequence")
	private Integer menuSequence;


    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    @JsonProperty("createdBy")
    private Integer createdById;

    @JsonProperty("updatedBy")
    private Integer updatedById;

    @JsonProperty("createdAt")
    private String createdAtId;

    @JsonProperty("updatedAt")
    private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
    }

    public RoleProgramDefaultDTO getRoleProgram() {
		return roleProgram;
	}

	public void setRoleProgram(RoleProgramDefaultDTO roleProgram) {
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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}    
    
}
