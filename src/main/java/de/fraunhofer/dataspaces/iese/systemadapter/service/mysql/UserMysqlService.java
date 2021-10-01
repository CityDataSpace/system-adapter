package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.auth.UserAuthApplicationWrapper;
import de.fraunhofer.dataspaces.iese.systemadapter.hashing.BCrypt;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Role;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.UserMysqlRepository;

@Service
public class UserMysqlService {
	
	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	@Autowired
	private RoleMysqlService roleMysqlService;
	
	@Autowired
	private BCrypt passwordEncoder;
	
	public List<User> findAll() {
		return userMysqlRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userMysqlRepository.findById(id);
	}
	
	public Optional<UserAuthApplicationWrapper> findByUsername(String username) {
		
		Optional<User> user = this.findByEmail(username);
		
		if(user.isPresent()) {
			User fetchedUser = user.get();
			
			List<Role> roles = roleMysqlService.findByUserId(fetchedUser.getId());
			
			
			Set<SimpleGrantedAuthority> permissions = new HashSet<>();
			
			for(Role role : roles) {
				permissions.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
			}
				 
			return Optional.of(new UserAuthApplicationWrapper(
					permissions,
					fetchedUser.getPassword(), 
					fetchedUser.getEmail(), 
					fetchedUser.isAccountNonExpired(), 
					fetchedUser.isAccountNonLocked(), 
					fetchedUser.isCredentialsNonExpired(), 
					fetchedUser.isEnabled()
					));
		}
		
		return null;
	}
	
	public Optional<User> findByEmail(String email) {
		return Optional.of(userMysqlRepository.findByEmail(email));
	}
	
	public boolean isUserAlreadyRegistered(String email) {
		Optional<User> userOptional = Optional.of(userMysqlRepository.findByEmail(email));
		
		if(userOptional.isPresent()) {
			return true;
		}
		
		return false;
	}
	
	public User findByEmailAndPassword(String email, String password) {
		
		Optional<User> userOptional = Optional.of(userMysqlRepository.findByEmail(email));
		
		if(userOptional.isEmpty()) {
			return null;
		}
		
		User user = userOptional.get();
		
		if(!passwordEncoder.getPasswordEncoder().matches(password, user.getPassword())) {
			return null;
		}
		
		return user;
	}
	
	public void save(User user) {
		user.setPassword(passwordEncoder.getPasswordEncoder().encode(user.getPassword()));
		userMysqlRepository.save(user);
	}
	
	public User saveAndReturn(User user) {
		user.setPassword(passwordEncoder.getPasswordEncoder().encode(user.getPassword()));
		return userMysqlRepository.save(user);
	}
	
	public void update(int id, User user) {
		
		Optional<User> dbUser = userMysqlRepository.findById(id);
		
		if(dbUser.isPresent()) {
			User retrievedUser = dbUser.get();
			
			retrievedUser.setName(user.getName());
			retrievedUser.setSurname(user.getSurname());
			retrievedUser.setPassword(user.getPassword());
			
			userMysqlRepository.save(retrievedUser);
		}
	}
	
	public User updateAndReturn(int id, User user) {
		
		Optional<User> dbUser = userMysqlRepository.findById(id);
		
		if(dbUser.isPresent()) {
			User retrievedUser = dbUser.get();
			
			retrievedUser.setName(user.getName());
			retrievedUser.setSurname(user.getSurname());
			retrievedUser.setPassword(user.getPassword());
			
			return userMysqlRepository.save(retrievedUser);
		}
		
		return null;
	}
	
	public void deleteById(int id) {
		try {
			userMysqlRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
