/**
 * 
 */
package w.fujiko.service.users;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.User;

/**
 * @author Try-Parser
 *
 */
@Service
public interface UserService extends UniversalCrud<User,Integer> {
	public Optional<User> getByUserId(Integer id, Boolean resign);
	public Optional<User> getByName(String email);
	public List<User> getUsersByNameOrCode(String key, Integer index);
	public Optional<User> getUndeletedUserByCode(Short code);
	public Optional<User> getByCode(Short code);
}
