package w.fujiko.service.systems;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;

import w.fujiko.model.masters.systems.ProgramCategory;

@Service
public interface ProgramCategoryService extends UniversalCrud<ProgramCategory,String> {
	public List<ProgramCategory> getGrantedProgramInCategoryOfUser(Integer userId);
	public List<ProgramCategory> getCategoryWithProgram();
}