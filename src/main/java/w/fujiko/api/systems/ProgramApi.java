package w.fujiko.api.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.service.systems.ProgramCategoryService;
import w.fujiko.service.systems.ProgramService;
import w.fujiko.dto.categories.CategoryRoleProgramDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

@RestController
@RequestMapping("/api/program")
public class ProgramApi 
	extends Api<ProgramService,Program,String> {

		@Autowired
		ProgramCategoryService programCategoryService;		
		ModelMapper modelMapper;

		public ProgramApi(){
			this.modelMapper = new ModelMapper();
			modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE);
		}

		@GetMapping("/grantedprograms/{userid}/{categoryid}")
		public @ResponseBody List<Program> getGrantedProgramInCategoryOfUser(@PathVariable(value="userid") final Integer userid,@PathVariable(value="categoryid") final char categoryid) {
			return service.getGrantedProgramInCategoryOfUser(userid,categoryid);
		}

		@GetMapping("/grantedprograms/{id}")
		public @ResponseBody List<CategoryRoleProgramDTO> getProgramsWithCorrespingingUserAccess(@PathVariable(value="id") final Integer userId) {
			
			List<CategoryRoleProgramDTO> categoryList = new ArrayList<>();
			

			programCategoryService
			.getCategoryWithProgram()
			.forEach(category->{

				category
				.getPrograms()
				.forEach(program->{
					program.setRoles(program
						.getRoles()
						.stream()
						.filter(role->role.getAuthorizedUser().getId()==userId).collect(Collectors.toSet()));
				});
				
				CategoryRoleProgramDTO categoryDTO = this.modelMapper
												     .map(category,CategoryRoleProgramDTO.class);
				categoryList.add(categoryDTO);

			});			
			return categoryList;
		}

		@GetMapping("/grantedprograms/")
		public @ResponseBody List<CategoryRoleProgramDTO> getProgramsWithCategory() {
			
			List<CategoryRoleProgramDTO> categoryList = new ArrayList<>();

			programCategoryService
			.getCategoryWithProgram()
			.forEach(category->{
				
				category
				.getPrograms()
				.forEach(program->{
					program.setRoles(new HashSet<>());
				});

				CategoryRoleProgramDTO categoryDTO =this.modelMapper
												     .map(category,CategoryRoleProgramDTO.class);
				categoryList.add(categoryDTO);

			});			
			return categoryList;
		}
}