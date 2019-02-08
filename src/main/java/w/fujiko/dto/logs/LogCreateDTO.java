package w.fujiko.dto.logs;

import java.util.Date;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import w.fujiko.util.common.enums.LogType;

public class LogCreateDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;

	@JsonIgnore
	public Date logDate = new Date();

	@NotNull
	private String programId;

	@NotNull
	private Integer programCommandId;

	private LogType type;

	private String message;

	@NotNull
	private Integer userId;

	private String ipAddress;


	public Date getLogDate(){
		return this.logDate;
	}

	public String getProgramId(){
		return this.programId;
	}

	public void setProgramId(String programId){
		this.programId = programId;
	}

	public Integer getProgramCommandId(){
		return this.programCommandId;
	}

	public void setProgramCommandId(Integer programCommandId){
		this.programCommandId = programCommandId;
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

	public Integer getUserId(){
		return this.userId;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public String getIpAddress(){
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

}