/**
 * 
 */
package fte.api.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import w.fujiko.dao.users.UserDao;
import w.fujiko.model.masters.users.RoleProgram;

/**
 * @author Try-Parser
 *
 */
@Service("Security")
@Transactional(readOnly=true)
public class SecureService implements UserDetailsService {

	@Autowired 
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		Short code = Short.parseShort(arg0);
		
		return (UserDetails) userDao.getByCode(code)
				.map(optUser -> 
					new User(optUser.getId().toString() +"_"+ optUser.getCode(), 
							optUser.getPassword(), 
							true, true, true, true, 
							grantAuthority(optUser.getCreatedAt().getRoles()))).orElseThrow(() -> new UsernameNotFoundException("Invalid Code"));
	}
	
	public Collection<? extends GrantedAuthority> grantAuthority(Set<RoleProgram> set) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(RoleProgram role: set) {
			authorities.add(new SimpleGrantedAuthority(role.getProgram().getName()+ "_" + role.getProgram().getId()));
		}
		return authorities;
	}
}