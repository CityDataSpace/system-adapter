package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Role;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.RoleMysqlRepository;

/**
 * This class provides a service to Role Mysql Repository
 */
@Service
public class RoleMysqlService {
	
	@Autowired
	private RoleMysqlRepository roleMysqlRepository;
	
	/**
	 * This function returns a list of roles
	 * @return
	 */
	public List<Role> findAll() {
		return roleMysqlRepository.findAll();
	}
	
	/**
	 * This function finds a role object in mysql database
	 * @param id
	 * @return role object
	 */
	public Optional<Role> findById(int id) {
		return roleMysqlRepository.findById(id);
	}
	
	/**
	 * This function finds a role object in mysql database
	 * @param user id
	 * @return role objects
	 */
	public List<Role> findByUserId(int id) {
		return roleMysqlRepository.findByUserId(id);
	}
	
	/**
	 * This function saves a role object to mysql database 
	 * @param registration
	 */
	public void save(Role role) {
		roleMysqlRepository.save(role);
	}
	
	/**
	 * This function saves a role object to mysql database
	 * @param registration
	 * @return saved role object
	 */
	public Role saveAndReturn(Role role) {
		return roleMysqlRepository.save(role);
	}
	
	/**
	 * This function updates a registration entry in mysql database
	 * @param id
	 * @param registration
	 */
	public void update(int id, Role role) {
		Optional<Role> dbRole = roleMysqlRepository.findById(id);
		
		if(dbRole.isPresent()) {
			Role retrievedRole = dbRole.get();
			
			retrievedRole.setUser(role.getUser());
			retrievedRole.setRole(role.getRole());
			
			roleMysqlRepository.save(retrievedRole);
		}
	}
	
	/**
	 * This function updates a role entry in mysql database
	 * @param id
	 * @param role
	 * @return updated role object
	 */
	public Role updateAndReturn(int id, Role role) {
		Optional<Role> dbRole = roleMysqlRepository.findById(id);
		
		if(dbRole.isPresent()) {
			Role retrievedRole = dbRole.get();
			
			retrievedRole.setUser(role.getUser());
			retrievedRole.setRole(role.getRole());
			
			return roleMysqlRepository.save(retrievedRole);
		}
		
		return null;
	}
	
	/**
	 * This function deletes a role object in mysql database
	 * @param id
	 */
	public void deleteById(int id) {
		try {
			roleMysqlRepository.deleteById(id);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
}
