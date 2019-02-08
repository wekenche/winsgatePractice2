package w.fujiko.dao.systems;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.systems.ProgramCategory;

public interface ProgramCategoryDao extends UniversalCrud<ProgramCategory,String> {
	List<ProgramCategory> getAllCategorySortedByCategoryId();
	List<ProgramCategory> getCategoryWithProgram();
}