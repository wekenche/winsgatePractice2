package w.fujiko.model.logs;

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

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCommand;
import w.fujiko.model.masters.users.User;
import w.fujiko.util.common.enums.LogType;

@Entity
@Table(name="mst_log")
public class Log implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_log_id",columnDefinition="int")
	public Integer id;

	@Column(name="log_datetime",columnDefinition="datetime")
	@NotNull
	public Date logDate;

	@ManyToOne
	@NotNull
	@JoinColumn(name="program_id",insertable=true,updatable=true)
	private Program program;

	@ManyToOne
	@NotNull
	@JoinColumn(name="program_command_id",insertable=true,updatable=true)
	private ProgramCommand programCommand;
	
	@Column(name="log_type",columnDefinition="varchar(10)")
	private LogType type;

	@Column(name="message",columnDefinition="text")
	private String message;

	@ManyToOne
	@NotNull
	@JoinColumn(name="user_id",insertable=true,updatable=true)
	private User user;
	
	@Column(name="ip_address",columnDefinition="nvarchar(20)")
	private String ipAddress;

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Date getLogDate(){
		return this.logDate;
	}

	public void setLogDate(Date logDate){
		this.logDate = logDate;
	}

	public Program getProgram(){
		return this.program;
	}

	public void setProgram(Program program){
		this.program = program;
	}

	public ProgramCommand getProgramCommand(){
		return this.programCommand;
	}

	public void setProgramCommand(ProgramCommand programCommand){
		this.programCommand = programCommand;
	}

	public LogType getType(){
		return this.type;
	}

	public void setType(LogType type){
		this.type = type;
	}

	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public User getUser(){
		return this.user;
	}

	public void setUser(User user){
		this.user = user;
	}

	public String getIpAddress(){
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

}