package w.fujiko.util.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import w.fujiko.model.masters.ProgramCategoryModel;
import w.fujiko.model.masters.ProgramModel;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCategory;

/**
 * @author festadillo
 *
 */
public class ProgramMapper {
	
	public static ProgramModel convertToProgramModel(Program entity) {
		ProgramModel model = new ProgramModel();
		if(entity != null) {
			model.setProgramId(entity.getId());
			model.setMenuSuence(entity.getMenuSequence());
			model.setName(entity.getName());
			model.setText(entity.getText());
			model.setCategoryId(entity.getCategory().getId());
		}
		return model;
	}
	
	public static List<ProgramModel> convertToProgramModels(List<Program> entities) {
		List<ProgramModel> models = new ArrayList<>();
		if(entities != null) {
			models = entities.stream()
					.map(el -> convertToProgramModel(el))
					.collect(Collectors.toList());
		}
		return models;
	}
	
	public static ProgramCategoryModel convertToProgramCategoryModel
		(ProgramCategory programCategoryEntity) {
		
		ProgramCategoryModel model = new ProgramCategoryModel();
		
		model.setCategoryId(programCategoryEntity.getId());
		model.setCategoryName(programCategoryEntity.getCategoryName());
		
		List<Program> programEntities = programCategoryEntity.getPrograms();
		if(programEntities != null) {
			List<ProgramModel> programModels = programEntities.stream()
				.map(el -> convertToProgramModel(el))
				.collect(Collectors.toList());
			model.setPrograms(programModels);
		}
		
		return model;		
	}
	
	public static List<ProgramCategoryModel> convertToProgramCategoryModels
		(List<ProgramCategory> entities) {
		
		List<ProgramCategoryModel> models = new ArrayList<>();
		if(entities != null) {
			models = entities.stream()
				.map(el -> convertToProgramCategoryModel(el))
				.collect(Collectors.toList());
		}
		return models;
	}

}