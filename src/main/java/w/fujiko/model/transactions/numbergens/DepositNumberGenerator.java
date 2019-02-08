package w.fujiko.model.transactions.numbergens;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import w.fujiko.model.masters.users.User;

@Entity
@Table(name="trn_deposit_numgen")
public class DepositNumberGenerator implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="slip_no",columnDefinition="int")
	public Integer slipNo;	

	@ManyToOne
	@NotNull
	@JoinColumn(name="user_id",insertable=true,updatable=false,nullable=false)
	private User user;
	
	@NotNull
	@Column(name="date_created",insertable=true,updatable=false,nullable=false)
	private Date dateGenerated;
	
	public Integer getSlipNo(){
		return this.slipNo;
	}

	public void setSlipNo(Integer slipNo){
		this.slipNo = slipNo;
	}

	public User getUser(){
		return this.user;
	}

	public void setUser(User user){
		this.user = user;
	}

	public Date getDateGenerated(){
		return this.dateGenerated;
	}

	public void setDateGenerated(Date dateGenerated){
		this.dateGenerated = dateGenerated;
	}
}