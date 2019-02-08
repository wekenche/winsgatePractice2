package w.fujiko.model.wrappers;

import java.util.List;

import w.fujiko.dto.roleprograms.commands.RoleProgramCommandDTO;

public class RoleProgramCommandWrapper{

    private List<RoleProgramCommandDTO> role_program_commands;

    public RoleProgramCommandWrapper(){

    }
    
    public List<RoleProgramCommandDTO> getRole_program_commands(){
        return this.role_program_commands;
    }
    public void setRole_program_commands(List<RoleProgramCommandDTO> roleProgramCommands){
        this.role_program_commands = roleProgramCommands;
    }
}
