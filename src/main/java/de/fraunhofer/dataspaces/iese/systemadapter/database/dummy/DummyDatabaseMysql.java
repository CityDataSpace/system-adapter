package de.fraunhofer.dataspaces.iese.systemadapter.database.dummy;

import static de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.role.ApplicationUserRole.*;
import static de.fraunhofer.dataspaces.iese.systemadapter.data.database.type.DatabaseType.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.dataspaces.iese.systemadapter.data.FraunhoferDataSpace;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Role;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.PayloadMysqlService;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.RegistrationMysqlService;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.RoleMysqlService;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

/**
 * This class contains entries for Mysql Database that are used throughout application. They do not represent real entries.
 */
@Component
@EnableTransactionManagement
public class DummyDatabaseMysql {
	
	@Autowired
	DummyUserCredentials dummyUserCredentials;
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@Autowired
	private RoleMysqlService roleMysqlService;
	
	@Autowired
	private PayloadMysqlService payloadMysqlService;
	
	@Autowired
	private RegistrationMysqlService registrationMysqlService;
	
	public void fillInDatabaseUser() {
		User user = new User();
		Role role = new Role();
		Registration registration = new Registration();
	
		
		user.setName(dummyUserCredentials.getName());
		user.setSurname(dummyUserCredentials.getSurname());
		user.setEmail(dummyUserCredentials.getUsername());
		user.setPassword(dummyUserCredentials.getPassword());
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		userMysqlService.save(user);
		
		role.setRole(STUDENT.name());
		role.setUser(user);
		roleMysqlService.save(role);
		
		registration.setRegisteredDatabases(MYSQL.getDatabaseType());
		registration.setUser(user);
		registrationMysqlService.save(registration);
	}
	
	
	public void fillInDatabasePayload() throws JsonProcessingException {
		Payload payload = new Payload();
		
		FraunhoferDataSpace fraunhoferDataSpace = new FraunhoferDataSpace();
		
		fraunhoferDataSpace
			.setName("My Policy")
			.setDuration("2 days")
			.setType("Restriction")
			.setDate(LocalDateTime.now());
		
		payload.setHeaderId(UUID.randomUUID());
		payload.setData(new ObjectMapper().writeValueAsString(fraunhoferDataSpace));
		
		payloadMysqlService.save(payload);
	}
	
	
	public Payload fillInDatabasePayloadAndReturn() throws JsonProcessingException {
		Payload payload = new Payload();
		
		FraunhoferDataSpace fraunhoferDataSpace = new FraunhoferDataSpace();
		
		fraunhoferDataSpace
			.setName("My Policy")
			.setDuration("2 days")
			.setType("Restriction")
			.setDate(LocalDateTime.now());
		
		payload.setHeaderId(UUID.randomUUID());
		payload.setData(new ObjectMapper().writeValueAsString(fraunhoferDataSpace));
		
		return payloadMysqlService.saveAndReturn(payload);
	}
}
