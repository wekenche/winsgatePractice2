/**
 * 
 */
package w.fujiko.service.repo.users;



import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.users.UserDao;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.users.UserService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class UserServiceRepo 
		extends UniversalServiceRepo<User, UserDao, Integer> 
		implements UserService {
	
	@Autowired UserDao userDao;

	@Override
	public Optional<User> getByUserId(Integer id, Boolean resign) {
		return userDao.getByUserId(id,resign);
	}
	
	public Optional<User> getByName(String email){
		return userDao.getByName(email);
	}
	
	@Override
	public List<User> getUsersByNameOrCode(String key, Integer index) {
		return dao.getUsersByNameOrCode(key, index);
	}
	
	@Override
	public Optional<User> getUndeletedUserByCode(Short code) {
		return dao.getUndeletedUserByCode(code);
	}

	@Override
	public Optional<User> getByCode(Short code) {
		return dao.getByCode(code);
	}
	
}