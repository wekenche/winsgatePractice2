package w.fujiko.service.systems;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;

import w.fujiko.model.masters.systems.Program;

@Service
public interface ProgramService extends UniversalCrud<Program,String> {
	public List<Program> getGrantedProgramInCategoryOfUser(Integer userId,char categoryId);
}