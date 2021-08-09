package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.UserMysqlRepository;

@Service
public class UserMysqlService {
	
	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	public List<User> findAll() {
		return userMysqlRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userMysqlRepository.findById(id);
	}
	
	public void save(User user) {
		userMysqlRepository.save(user);
	}
	
	public User saveAndReturn(User user) {
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
