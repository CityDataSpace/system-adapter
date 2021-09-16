package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Role;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.RoleMysqlRepository;

@Service
public class RoleMysqlService {
	
	@Autowired
	private RoleMysqlRepository roleMysqlRepository;
	
	public List<Role> findAll() {
		return roleMysqlRepository.findAll();
	}
	
	public Optional<Role> findById(int id) {
		return roleMysqlRepository.findById(id);
	}
	
	public List<Role> findByUserId(int id) {
		return roleMysqlRepository.findByUserId(id);
	}
	
	public void save(Role role) {
		roleMysqlRepository.save(role);
	}
	
	public Role saveAndReturn(Role role) {
		return roleMysqlRepository.save(role);
	}
	
	public void update(int id, Role role) {
		Optional<Role> dbRole = roleMysqlRepository.findById(id);
		
		if(dbRole.isPresent()) {
			Role retrievedRole = dbRole.get();
			
			retrievedRole.setUser(role.getUser());
			retrievedRole.setRole(role.getRole());
			
			roleMysqlRepository.save(retrievedRole);
		}
	}
	
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
	
	public void deleteById(int id) {
		try {
			roleMysqlRepository.deleteById(id);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
}
