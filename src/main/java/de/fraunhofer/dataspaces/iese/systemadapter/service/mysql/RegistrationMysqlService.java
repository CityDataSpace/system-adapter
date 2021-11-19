package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.RegistrationMysqlRepository;

/**
 * This class provides a service to Registration Mysql Repository
 */
@Service
public class RegistrationMysqlService {
	
	@Autowired
	private RegistrationMysqlRepository registrationMysqlRepository;
	
	/**
	 * This function returns a list of registrations
	 * @return
	 */
	public List<Registration> findAll() {
		return registrationMysqlRepository.findAll();
	}
	
	/**
	 * This function finds a registration object in mysql database
	 * @param id
	 * @return registration object
	 */
	public Optional<Registration> findById(int id) {
		return registrationMysqlRepository.findById(id);
	}
	
	/**
	 * This function finds a registration object in mysql database
	 * @param user id
	 * @return registration objects
	 */
	public List<Registration> findByUserId(int id) {
		return registrationMysqlRepository.findByUserId(id);
	}
	
	/**
	 * This function saves a registration object to mysql database 
	 * @param registration
	 */
	public void save(Registration registration) {
		registrationMysqlRepository.save(registration);
	}
	
	/**
	 * This function saves a registration object to mysql database
	 * @param registration
	 * @return saved registration object
	 */
	public Registration saveAndReturn(Registration registration) {
		return registrationMysqlRepository.save(registration);
	}
	
	/**
	 * This function updates a registration entry in mysql database
	 * @param id
	 * @param registration
	 */
	public void update(int id, Registration registration) {
		Optional<Registration> dbRegistration = registrationMysqlRepository.findById(id);
		
		if(dbRegistration.isPresent()) {
			Registration retrievedRegistration = dbRegistration.get();
			
			retrievedRegistration.setUser(registration.getUser());
			retrievedRegistration.setRegisteredDatabases(registration.getRegisteredDatabases());
			
			registrationMysqlRepository.save(retrievedRegistration);
		}
	}
	
	/**
	 * This function updates a registration entry in mysql database
	 * @param id
	 * @param registration
	 * @return updated registration object
	 */
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
	
	/**
	 * This function deletes a registration object in mysql database
	 * @param id
	 */
	public void deleteById(int id) {
		try {
			registrationMysqlRepository.deleteById(id);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
}
