package w.fujiko.dao.repo.users;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import w.fujiko.dao.users.RoleProgramDao;
import w.fujiko.model.masters.users.ProgramPk;
import w.fujiko.model.masters.users.RoleProgram;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class RoleProgramDaoRepo 
	extends UniversalCrudRepo<RoleProgram ,ProgramPk>
	implements RoleProgramDao {

		public RoleProgramDaoRepo() {
			super();
			type = RoleProgram.class;
		}
}
