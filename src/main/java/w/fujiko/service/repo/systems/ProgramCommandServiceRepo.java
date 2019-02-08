package w.fujiko.service.repo.systems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCommand;
import w.fujiko.dao.systems.ProgramCommandDao;
import w.fujiko.service.systems.ProgramCategoryService;
import w.fujiko.service.systems.ProgramCommandService;

@Service
@Transactional
public class ProgramCommandServiceRepo extends UniversalServiceRepo<ProgramCommand, ProgramCommandDao, Integer>
		implements ProgramCommandService {
	
	@Autowired 
	ProgramCategoryService programCategoryService;

	@Override
	public List<ProgramCategory> getGrantedProgramCommandByUser(Integer userId) {
		List<Program> programList = dao.getGrantedProgramCommandByUser(userId);
		List<ProgramCategory> programCategoryList = new ArrayList<>();
		programList.stream().collect(Collectors.groupingBy(Program::getCategoryId,Collectors.toList()))
				.entrySet().forEach(map->{					
					programCategoryService.get(map.getKey()).ifPresent(cat->{
						cat.setPrograms(map.getValue());
						programCategoryList.add(cat);
					});
		});
		return programCategoryList;
	}

	@Override
	public List<ProgramCommand> getGrantedCommandOfUserByPage(int userId, String pageId) {
		return dao.getGrantedCommandOfUserByPage(userId, pageId);
	}
		
}