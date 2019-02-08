package w.fujiko.api.systems;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.ProgramCategoryModel;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.service.systems.ProgramCategoryService;
import w.fujiko.util.mapper.ProgramMapper;

@RestController
@RequestMapping("/api/program/category")
public class ProgramCategoryApi 
	extends Api<ProgramCategoryService,ProgramCategory,String> {
		
		@GetMapping("/grantedprograms/{id}")
		public @ResponseBody List<ProgramCategoryModel> getUserGrantedProgramsByCategory(@PathVariable(value="id") final Integer userId) {
			List<ProgramCategory> entities = service.getGrantedProgramInCategoryOfUser(userId);
			List<ProgramCategoryModel> models = ProgramMapper.convertToProgramCategoryModels(entities);
			return models;
		}
		
}