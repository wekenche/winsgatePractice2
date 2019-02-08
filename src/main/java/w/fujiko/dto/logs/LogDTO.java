package w.fujiko.dto.logs;

import java.util.Date;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import w.fujiko.util.common.enums.LogType;

public class LogDTO implements Serializable {

	private static final long serialVersionUID = 7257921460512063270L;

	@NotNull
	public Integer id;

	@NotNull
	public Date logDate;

	@NotNull
	private String programId;

	private String programText;

	@NotNull
	private Integer programCommandId;

	private String programCommandName;

	private String programCommandKeyName;

	private LogType type;

	private String message;

	@NotNull
	private Short userCode;

	private String userUsername;

	private String userUsernameKana;

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

	public String getProgramId(){
		return this.programId;
	}

	public void setProgramId(String programId){
		this.programId = programId;
	}

	public String getProgramText(){
		return this.programText;
	}

	public void setProgramText(String programText){
		this.programText = programText;
	}

	public Integer getProgramCommandId(){
		return this.programCommandId;
	}

	public void setProgramCommandId(Integer programCommandId){
		this.programCommandId = programCommandId;
	}

	public String getProgramCommandName(){
		return this.programCommandName;
	}

	public void setProgramCommandName(String programCommandName){
		this.programCommandName = programCommandName;
	}

	public String getProgramCommandKeyName(){
		return this.programCommandKeyName;
	}

	public void setProgramCommandKeyName(String programCommandKeyName){
		this.programCommandKeyName = programCommandKeyName;
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
	
	public Short getUserCode(){
		return this.userCode;
	}

	public void setUserCode(Short userCode){
		this.userCode = userCode;
	}

	public String getUserUsername(){
		return this.userUsername;
	}

	public void setUserUsername(String userUsername){
		this.userUsername = userUsername;
	}

	public String getUserUsernameKana(){
		return this.userUsernameKana;
	}

	public void setUserUsernameKana(String userUsernameKana){
		this.userUsernameKana = userUsernameKana;
	}

	public String getIpAddress(){
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

}