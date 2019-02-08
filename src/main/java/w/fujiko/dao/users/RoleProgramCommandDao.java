package w.fujiko.dao.users;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgramCommand;

public interface RoleProgramCommandDao extends UniversalCrud<RoleProgramCommand,RoleProgramCommandPk> {
    public List<RoleProgramCommand> findByRoleProgram(RoleProgram roleProgram);
    void batchSave(List<RoleProgramCommand> roleProgramCommands) throws ConstraintViolationException, PersistenceException;
    void batchDelete(List<RoleProgramCommand> roleProgramCommands) throws ConstraintViolationException, PersistenceException;
}