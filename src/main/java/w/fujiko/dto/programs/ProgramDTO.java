/**
 * 
 */
package w.fujiko.dto.programs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yagami
 *
 */
public class ProgramDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("id")
		private String id;

    @JsonProperty("categoryId")
		private String categoryId;

    @JsonProperty("menuSequence")
		private Integer menuSequence;
    
    @JsonProperty("name")
		private String name;

    @JsonProperty("text")
		private String text;

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

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
