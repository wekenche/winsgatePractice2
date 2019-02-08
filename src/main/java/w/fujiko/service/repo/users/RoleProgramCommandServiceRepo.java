/**
 * 
 */
package w.fujiko.service.repo.users;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.users.RoleProgramCommandDao;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgramCommand;
import w.fujiko.service.users.RoleProgramCommandService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class RoleProgramCommandServiceRepo
		extends UniversalServiceRepo<RoleProgramCommand, RoleProgramCommandDao, RoleProgramCommandPk>
		implements RoleProgramCommandService {

	@Override
	public List<RoleProgramCommand> findByRoleProgram(RoleProgram roleProgram) {
		return dao.findByRoleProgram(roleProgram);
	}

	@Override
	public void batchSave(List<RoleProgramCommand> roleProgramCommands)
			throws ConstraintViolationException, PersistenceException {
		dao.batchSave(roleProgramCommands);
	}

	@Override
	public void batchDelete(List<RoleProgramCommand> roleProgramCommands)
			throws ConstraintViolationException, PersistenceException {
		dao.batchDelete(roleProgramCommands);
	}
}
