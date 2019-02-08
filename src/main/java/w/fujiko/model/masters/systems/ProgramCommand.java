/**
 * 
 */
package w.fujiko.model.masters.systems;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;

import w.fujiko.model.masters.users.RoleProgramCommand;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

/**
 * @author yagami
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "id",scope=ProgramCommand.class)
@Entity
@Table(name="sys_prog_command")

//use for mapping database columns to entity attributes
@SqlResultSetMapping(
  name="ProgramCommandMapping",
  entities={
    @EntityResult(
      entityClass = ProgramCommand.class,
        fields={
          @FieldResult(name="id",column="id"),
		  @FieldResult(name="name", column="prog_command_name"),
		  @FieldResult(name="keyName", column="prog_command_key"),
		  @FieldResult(name="keyCode", column="prog_command_key_code"),
		  @FieldResult(name="isPermissionNeeded", column="is_permission_needed"),
		  @FieldResult(name="programId", column="sys_prog_id"),
		  @FieldResult(name="commandFunction", column="command_function")})})
public class ProgramCommand implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7257921460512063270L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="sys_prog_id", insertable=false, updatable = false)
	@JsonBackReference(value="programCommand")
	@NotNull
	private Program program;

	@Column(name="sys_prog_id",columnDefinition="char(7)")
	@NotNull
	private String programId;

	@NotNull
	@Column(name="prog_command_name", columnDefinition = "nvarchar(100)")
	private String name;

	@Column(name="prog_command_key", columnDefinition = "nvarchar(15)",nullable=true)
	private String keyName;

	@Column(name="prog_command_key_code", columnDefinition = "char(3)",nullable=true)
	private Integer keyCode;

	@Column(name="command_function",columnDefinition="nvarchar(35)",nullable=true)
	private String commandFunction;

	@Column(name="is_permission_needed",columnDefinition="bit default 0",nullable=true)
	private boolean isPermissionNeeded;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="command.command_id")
	@JsonManagedReference(value="programCommand")
	private Set<RoleProgramCommand> roleProgramCommands = new HashSet<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Integer getKeyCode() {
		return this.keyCode;
	}

	public void setKeyCode(Integer keyCode) {
		this.keyCode = keyCode;
	}

	public String getCommandFunction() {
		return this.commandFunction;
	}

	public void setCommandFunction(String commandFunction) {
		this.commandFunction = commandFunction;
	}

	public boolean getIsPermissionNeeded() {
		return this.isPermissionNeeded;
	}

	public void getIsPermissionNeeded(boolean isPermissionNeeded) {
		this.isPermissionNeeded = isPermissionNeeded;
	}
	 
	public Set<RoleProgramCommand> getRoleProgramCommands() {
		return roleProgramCommands;
	}

	public void setRoleProgramCommands(Set<RoleProgramCommand> roleProgramCommands) {
		this.roleProgramCommands = roleProgramCommands;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}