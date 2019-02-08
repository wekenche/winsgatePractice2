package w.fujiko.dao.systems;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.systems.Program;

public interface ProgramDao extends UniversalCrud<Program,String> {
	public List<Program> getGrantedProgramInCategoryOfUser(Integer userId,char categoryId);
	public List<Program> getGrantedProgramsOfUser(Integer userId);
}