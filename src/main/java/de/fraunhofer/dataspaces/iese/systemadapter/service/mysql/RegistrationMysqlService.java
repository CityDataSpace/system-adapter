package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.RegistrationMysqlRepository;

@Service
public class RegistrationMysqlService {
	
	@Autowired
	private RegistrationMysqlRepository registrationMysqlRepository;
	
	public List<Registration> findAll() {
		return registrationMysqlRepository.findAll();
	}
	
	public Optional<Registration> findById(int id) {
		return registrationMysqlRepository.findById(id);
	}
	
	public List<Registration> findByUserId(int id) {
		return registrationMysqlRepository.findByUserId(id);
	}
	
	public void save(Registration registration) {
		registrationMysqlRepository.save(registration);
	}
	
	public Registration saveAndReturn(Registration registration) {
		return registrationMysqlRepository.save(registration);
	}
	
	public void update(int id, Registration registration) {
		Optional<Registration> dbRegistration = registrationMysqlRepository.findById(id);
		
		if(dbRegistration.isPresent()) {
			Registration retrievedRegistration = dbRegistration.get();
			
			retrievedRegistration.setUser(registration.getUser());
			retrievedRegistration.setRegisteredDatabases(registration.getRegisteredDatabases());
			
			registrationMysqlRepository.save(retrievedRegistration);
		}
	}
	
	public Registration updateAndReturn(int id, Registration registration) {
		Optional<Registration> dbRegistration = registrationMysqlRepository.findById(id);
		
		if(dbRegistration.isPresent()) {
			Registration retrievedRegistration = dbRegistration.get();
			
			retrievedRegistration.setUser(registration.getUser());
			retrievedRegistration.setRegisteredDatabases(registration.getRegisteredDatabases());
			
			return registrationMysqlRepository.save(retrievedRegistration);
		}
		
		return null;
	}
	
	public void deleteById(int id) {
		try {
			registrationMysqlRepository.deleteById(id);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
}
