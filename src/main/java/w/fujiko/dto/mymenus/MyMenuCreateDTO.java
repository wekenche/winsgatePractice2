/**
 * 
 */
package w.fujiko.dto.mymenus;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class MyMenuCreateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    private String programId;

    private Integer authorizedUserId;
    
    @JsonProperty("menuSequence")
	private Integer menuSequence;

    @JsonIgnore
    private Date dateCreated = new Date();

    @JsonProperty("createdBy")
    @NotNull
    private Integer createdById;

    @JsonProperty("createdAt")
    @NotNull
    private String createdAtId;
    
    public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
    }

    public Integer getAuthorizedUserId() {
		return authorizedUserId;
	}

	public void setAuthorizedUserId(Integer authorizedUserId) {
		this.authorizedUserId = authorizedUserId;
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

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public String getCreatedAtId() {
		return createdAtId;
	}

	public void setCreatedAtId(String createdAtId) {
		this.createdAtId = createdAtId;
	}
}
