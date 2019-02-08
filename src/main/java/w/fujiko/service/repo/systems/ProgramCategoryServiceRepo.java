package w.fujiko.service.repo.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.systems.ProgramCategoryDao;
import w.fujiko.dao.systems.ProgramDao;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.service.systems.ProgramCategoryService;

@Service
@Transactional
public class ProgramCategoryServiceRepo 
	extends UniversalServiceRepo<ProgramCategory,ProgramCategoryDao,String> 
	implements ProgramCategoryService {
	
		@Autowired ProgramDao programDao;		

		@Override
		public List<ProgramCategory> getGrantedProgramInCategoryOfUser(Integer userId) {
			
			List<ProgramCategory> allCategoryEntities = this.removeProgramsInCategories(dao.getAllCategorySortedByCategoryId());
			List<Program> programEntities = programDao.getGrantedProgramsOfUser(userId);
			List<ProgramCategory> categoryEntities = new ArrayList<>();
			if(programEntities != null) {

				programEntities
				.stream()
				.collect(Collectors.groupingBy(Program::getCategory,Collectors.toList()))
				.entrySet()
				.forEach(el->{					
					ProgramCategory programCategory=el.getKey();
					programCategory.setPrograms(el.getValue());
					categoryEntities.add(programCategory);
				});
				
				allCategoryEntities = transform(allCategoryEntities, categoryEntities);
				
				return allCategoryEntities;
			}
			return null;
		}		
		
		private List<ProgramCategory> removeProgramsInCategories(List<ProgramCategory> programCategories){
			List<ProgramCategory> newCategoryList = new ArrayList<>();
			programCategories.forEach(el->{
				el.setPrograms(new ArrayList<>());
				newCategoryList.add(el);
			});
			return newCategoryList;
		}
		private List<ProgramCategory> removeDuplicate(List<ProgramCategory> programEntities) {
			return new ArrayList<>(new HashSet<>(programEntities));
		}
		
		private List<ProgramCategory> transform(List<ProgramCategory> allCategoryEntities, 
				List<ProgramCategory> currentCategoryEntities) {
			List<ProgramCategory> newCategoryList = new ArrayList<>();
			for(ProgramCategory programCategory : allCategoryEntities) {
				String programCategoryId = programCategory.getId();
				for(ProgramCategory currentProgramCategory : currentCategoryEntities) {
					String currentCategoryId = currentProgramCategory.getId();
					if(programCategoryId.equals(currentCategoryId))
						programCategory = currentProgramCategory;					
				}
				newCategoryList.add(programCategory);
			}
			return newCategoryList;
		}

	@Override
	public List<ProgramCategory> getCategoryWithProgram() {
		return dao.getCategoryWithProgram();
	}
}