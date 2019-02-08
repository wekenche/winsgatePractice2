package w.fujiko.dao.systems;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCommand;

public interface ProgramCommandDao extends UniversalCrud<ProgramCommand,Integer> {
    public List<Program> getGrantedProgramCommandByUser(Integer userId);
    public List<ProgramCommand> getGrantedCommandOfUserByPage(int userId,String pageId);
}