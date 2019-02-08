package w.fujiko.dao.users;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.User;

public interface UserDao extends UniversalCrud<User,Integer> {
	public Optional<User> getByName(String email);
	public Optional<User> getByUserId(Integer id, Boolean resign);
	public Optional<User> getByCode(Short code);
	public List<User> getUsersByNameOrCode(String key, Integer index);
	public Optional<User> getUndeletedUserByCode(Short code);
}