package w.fujiko.model.wrappers;

import java.util.List;
import w.fujiko.dto.roleprograms.RoleProgramDTO;

public class RoleProgramWrapper{

    private List<RoleProgramDTO> role_program;

    public RoleProgramWrapper(){

    }
    
    public List<RoleProgramDTO> getRole_program(){
        return this.role_program;
    }
    public void setRole_program(List<RoleProgramDTO> roleProgram){
        this.role_program = roleProgram;
    }
}
