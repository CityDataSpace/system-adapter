package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import static de.fraunhofer.dataspaces.iese.systemadapter.data.database.type.DatabaseType.*;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import de.fraunhofer.dataspaces.iese.systemadapter.configuration.database.PersistanceMysqlConfiguration;
import de.fraunhofer.dataspaces.iese.systemadapter.hashing.BCrypt;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest(classes = {PersistanceMysqlConfiguration.class, UserMysqlService.class, RegistrationMysqlService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
@Import(BCrypt.class)
@ContextConfiguration(classes= {RoleMysqlService.class})
public class RegistrationMysqlServiceTest {
	
	@Autowired 
	private RegistrationMysqlService registrationMysqlService;
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private User user;
	
	private Registration registration;
	
	private Registration newlyCreatedRegistration;
	
	private final String NAME 	  = "Arian";
	private final String SURNAME  = "Ajdari";
	private final String EMAIL    = "arianajdari94@gmail.com";
	private final String PASSWORD = "new_password";
	
	private final int REGISTERED_DATABASES = MYSQL.getDatabaseType();
	
	private final int UPDATED_DATABASES = POSTGRES.getDatabaseType();
	
	@PostConstruct
	public void init() {
		user = new User();
		registration = new Registration();
		
		user.setName(NAME);
		user.setSurname(SURNAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		userMysqlService.save(user);
		
		registration.setRegisteredDatabases(REGISTERED_DATABASES);
		registration.setUser(user);
		newlyCreatedRegistration = registrationMysqlService.saveAndReturn(registration);	
	}
	
	@Test
	void A_checkRegistrationCreate() {
		assertEquals(registration.getId(), newlyCreatedRegistration.getId());
		assertEquals(REGISTERED_DATABASES, newlyCreatedRegistration.getRegisteredDatabases());
		
		assertEquals(NAME, newlyCreatedRegistration.getUser().getName());
		assertEquals(SURNAME, newlyCreatedRegistration.getUser().getSurname());
		assertEquals(EMAIL, newlyCreatedRegistration.getUser().getEmail());
		assertThat(passwordEncoder.matches(PASSWORD, newlyCreatedRegistration.getUser().getPassword())).isTrue();	
	}
	
	@Test
	void B_checkRegistrationRead() {
		Optional<Registration> registration = registrationMysqlService.findById(newlyCreatedRegistration.getId());
		
		assertThat(registration.isPresent()).isTrue();
		
		if(registration.isPresent()) {
			Registration retrievedRegistration = registration.get();
			
			assertEquals(retrievedRegistration.getId(), newlyCreatedRegistration.getId());
			assertEquals(retrievedRegistration.getRegisteredDatabases(), newlyCreatedRegistration.getRegisteredDatabases());
		}
	}
	
	@Test
	void C_checkRegistrationUpdate() {
		Optional<Registration> registration = registrationMysqlService.findById(newlyCreatedRegistration.getId());
		
		assertThat(registration.isPresent()).isTrue();
		
		if(registration.isPresent()) {
			Registration retrievedRegistration = registration.get();
			
			retrievedRegistration.setRegisteredDatabases(UPDATED_DATABASES);
			
			registrationMysqlService.update(retrievedRegistration.getId(), retrievedRegistration);
			
			registration = registrationMysqlService.findById(newlyCreatedRegistration.getId());
			
			assertThat(registration.isPresent()).isTrue();
			
			if(registration.isPresent()) {
				Registration toBeCheckedRegistration = registration.get();
				
				assertEquals(UPDATED_DATABASES, toBeCheckedRegistration.getRegisteredDatabases());
			}
		}
		
	}
	
	@Test
	void D_checkRegistrationDelete() {
		try {
			registrationMysqlService.deleteById(newlyCreatedRegistration.getId());
		} catch(IllegalArgumentException iae) {
			throw(iae);
		}
	}
}
