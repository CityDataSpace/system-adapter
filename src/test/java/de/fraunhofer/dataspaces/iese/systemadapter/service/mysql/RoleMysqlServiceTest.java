package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import static de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.role.ApplicationUserRole.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.fraunhofer.dataspaces.iese.systemadapter.configuration.database.PersistanceMysqlConfiguration;
import de.fraunhofer.dataspaces.iese.systemadapter.hashing.BCrypt;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Role;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest(classes = {PersistanceMysqlConfiguration.class, UserMysqlService.class, RoleMysqlService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
@Import(BCrypt.class)
public class RoleMysqlServiceTest {

	@Autowired
	private RoleMysqlService roleMysqlService;
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private User user;
	
	private Role role;
	
	private Role newlyCreatedRole;
	
	private final String NAME 	  = "Arian";
	private final String SURNAME  = "Ajdari";
	private final String EMAIL    = "arianajdari94@gmail.com";
	private final String PASSWORD = "new_password";
	
	private final String ROLE = STUDENT.name();
	
	private final String UPDATED_ROLE = ADMIN.name();
	
	@PostConstruct
	public void init() {
		user = new User();
		role = new Role();
		
		user.setName(NAME);
		user.setSurname(SURNAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		userMysqlService.save(user);
		
		role.setRole(ROLE);
		role.setUser(user);
		newlyCreatedRole = roleMysqlService.saveAndReturn(role);
	}
	
	@Test
	void A_checkRegistrationCreate() {
		assertEquals(role.getId(), newlyCreatedRole.getId());
		assertEquals(role.getRole(), newlyCreatedRole.getRole());
		
		assertEquals(NAME, newlyCreatedRole.getUser().getName());
		assertEquals(SURNAME, newlyCreatedRole.getUser().getSurname());
		assertEquals(EMAIL, newlyCreatedRole.getUser().getEmail());
		assertThat(passwordEncoder.matches(PASSWORD, newlyCreatedRole.getUser().getPassword())).isTrue();
	}
	
	@Test
	void B_checkRegistrationRead() {
		Optional<Role> role = roleMysqlService.findById(newlyCreatedRole.getId());
		
		assertThat(role.isPresent()).isTrue();
		
		if(role.isPresent()) {
			Role retrievedRole = role.get();
			
			assertEquals(retrievedRole.getId(), newlyCreatedRole.getId());
			assertEquals(retrievedRole.getRole(), newlyCreatedRole.getRole());
		}
	}
	
	@Test
	void C_checkRegistrationUpdate() {
		Optional<Role> role = roleMysqlService.findById(newlyCreatedRole.getId());
		
		assertThat(role.isPresent()).isTrue();
		
		if(role.isPresent()) {
			Role retrievedRole = role.get();
			
			retrievedRole.setRole(UPDATED_ROLE);
			
			roleMysqlService.update(retrievedRole.getId(), retrievedRole);
			
			role = roleMysqlService.findById(newlyCreatedRole.getId());
			
			assertThat(role.isPresent()).isTrue();
			
			if(role.isPresent()) {
				Role toBeCheckedRole = role.get();
				
				assertEquals(UPDATED_ROLE, toBeCheckedRole.getRole());
			}
		}
		
	}
	
	@Test
	void D_checkRegistrationDelete() {
		try {
			roleMysqlService.deleteById(newlyCreatedRole.getId());
		} catch(IllegalArgumentException iae) {
			throw(iae);
		}
	}
	
}
