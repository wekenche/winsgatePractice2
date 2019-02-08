/**
 * 
 */
package w.fujiko.dto.mymenus;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class MyMenuUpdateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("id")
    @NotNull
    private Integer id;
    
    @JsonProperty("programId")
    @NotEmpty
    private String programId;

    @JsonProperty("authorizedUserId")
    @NotNull
    private Integer authorizedUserId;

    @JsonProperty("menuSequence")
    @NotNull
	private Integer menuSequence;

    @JsonIgnore
    private Date dateUpdated = new Date();

    @JsonProperty("updatedBy")
    @NotNull
    private Integer updatedById;

    @JsonProperty("updatedAt")
    @NotNull
    private String updatedAtId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
    }

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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedAtId() {
		return updatedAtId;
	}

	public void setUpdatedAtId(String updatedAtId) {
		this.updatedAtId = updatedAtId;
	}    
    
}
