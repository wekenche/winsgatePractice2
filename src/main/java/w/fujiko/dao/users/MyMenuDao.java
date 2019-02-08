package w.fujiko.dao.users;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.model.masters.users.RoleProgram;

public interface MyMenuDao extends UniversalCrud<MyMenu,Integer> {
	public List<MyMenu> getCustomizeMenuOfUser(Integer userid);
	public List<MyMenu> findByRoleProgram(RoleProgram roleProgram);
}