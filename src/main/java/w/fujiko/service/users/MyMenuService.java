package w.fujiko.service.users;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;

import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.model.masters.users.RoleProgram;

@Service
public interface MyMenuService extends UniversalCrud<MyMenu,Integer> {
	public List<MyMenu> getCustomizeMenuOfUser(Integer userid);
	public List<MyMenu> findByRoleProgram(RoleProgram roleProgram);
}