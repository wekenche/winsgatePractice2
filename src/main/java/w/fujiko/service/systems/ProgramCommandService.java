package w.fujiko.service.systems;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCommand;

@Service
public interface ProgramCommandService extends UniversalCrud<ProgramCommand,Integer> {
    public List<ProgramCategory> getGrantedProgramCommandByUser(Integer userId);
    public List<ProgramCommand> getGrantedCommandOfUserByPage(int userId,String pageId);
}