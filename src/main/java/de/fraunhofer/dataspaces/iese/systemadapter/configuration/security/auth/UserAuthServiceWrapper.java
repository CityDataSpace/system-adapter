package de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

/**
 * This enum contains user auth service wrapper
 */
@Service
public class UserAuthServiceWrapper implements UserDetailsService {
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userMysqlService.findByUsername(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", email)));
	}

}
