package w.fujiko.model.transactions.numbergens;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import w.fujiko.model.masters.users.User;

@Entity
@Table(name="trn_construction_numgen")
public class ConstructionNumberGenerator implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@NotNull
	@JoinColumn(name="user_id",insertable=true,updatable=false,nullable=false)
	private User user;

	@Column(name="slip_no",columnDefinition="int")
	public int slipNumber;	
	
	@NotNull
    @Column(name="date_created",insertable=true,updatable=false,nullable=false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

    @UpdateTimestamp
    @Column(name="date_updated",insertable=true,updatable=true,nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    
	public int getSlipNumber(){
		return this.slipNumber;
	}

	public void setSlipNumber(int slipNumber){
		this.slipNumber = slipNumber;
	}

	public User getUser(){
		return this.user;
	}

	public void setUser(User user){
		this.user = user;
	}

	public Date getDateCreated(){
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated){
		this.dateCreated = dateCreated;
    }
    
    public Date getDateUpdated(){
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated){
		this.dateUpdated = dateUpdated;
    }
    
}