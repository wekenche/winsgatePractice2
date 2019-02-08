/**
 * 
 */
package w.fujiko.service.repo.users;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.users.RoleProgramDao;
import w.fujiko.model.masters.users.ProgramPk;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.service.users.RoleProgramService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class RoleProgramServiceRepo 
		extends UniversalServiceRepo<RoleProgram, RoleProgramDao, ProgramPk> 
		implements RoleProgramService {}
