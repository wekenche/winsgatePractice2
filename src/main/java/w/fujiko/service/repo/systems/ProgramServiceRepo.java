package w.fujiko.service.repo.systems;

import java.lang.Override;
import java.util.List;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import fte.api.universal.UniversalServiceRepo;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.dao.systems.ProgramDao;
import w.fujiko.service.systems.ProgramService;

@Service
@Transactional
public class ProgramServiceRepo 
	extends UniversalServiceRepo<Program,ProgramDao,String> 
	implements ProgramService {

		@Override
		public List<Program> getGrantedProgramInCategoryOfUser(Integer userId,char categoryId) {
			return dao.getGrantedProgramInCategoryOfUser(userId,categoryId);
		}
		
}