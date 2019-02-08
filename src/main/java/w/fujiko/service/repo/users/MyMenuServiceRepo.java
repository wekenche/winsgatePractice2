package w.fujiko.service.repo.users;

import java.lang.Override;
import java.util.List;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import fte.api.universal.UniversalServiceRepo;

import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.dao.users.MyMenuDao;
import w.fujiko.service.users.MyMenuService;

@Service
@Transactional
public class MyMenuServiceRepo 
	extends UniversalServiceRepo<MyMenu,MyMenuDao,Integer> 
	implements MyMenuService {

		@Override
		public List<MyMenu> getCustomizeMenuOfUser(Integer userid) {
		return dao.getCustomizeMenuOfUser(userid);
	}

	@Override
	public List<MyMenu> findByRoleProgram(RoleProgram roleProgram) {
			return dao.findByRoleProgram(roleProgram);
		}
}