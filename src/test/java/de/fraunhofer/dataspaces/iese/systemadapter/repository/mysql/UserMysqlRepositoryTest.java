package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.annotation.PostConstruct;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.configuration.BCryptConfig;
import de.fraunhofer.dataspaces.iese.systemadapter.configuration.PersistanceMysqlConfiguration;


@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest(classes = PersistanceMysqlConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
@Import(BCryptConfig.class)
public class UserMysqlRepositoryTest {
	
	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private User user;
	
	private User newlyCreatedUser;
	
	private final String NAME 	  = "Arian";
	private final String SURNAME  = "Ajdari";
	private final String EMAIL    = "arianajdari94@gmail.com";
	private final String PASSWORD = "new_password";
	
	private final String UPDATED_NAME = "James";
	
	public UserMysqlRepositoryTest() {
		
	}
	
	@PostConstruct
	public void init() {	
		user = new User();
		
		user.setName(NAME);
		user.setSurname(SURNAME);
		user.setEmail(EMAIL);
		user.setPassword(passwordEncoder.encode(PASSWORD));
		
		newlyCreatedUser = userMysqlRepository.save(user);		
	}
	
	@Test
	void A_checkUserCreate() {
		assertEquals(user.getId(), newlyCreatedUser.getId());
		assertEquals(NAME, newlyCreatedUser.getName());
		assertEquals(SURNAME, newlyCreatedUser.getSurname());
		assertEquals(EMAIL, newlyCreatedUser.getEmail());	
		assertThat(passwordEncoder.matches(PASSWORD, newlyCreatedUser.getPassword())).isTrue();

	}
	
	@Test
	void B_checkUserRead() {
		Optional<User> user = userMysqlRepository.findById(newlyCreatedUser.getId());
		
		assertThat(user.isPresent()).isTrue();
		
		if(user.isPresent()) {
			User retrievedUser = user.get();
			
			assertEquals(retrievedUser.getId(), newlyCreatedUser.getId());
			assertEquals(retrievedUser.getName(), newlyCreatedUser.getName());
			assertEquals(retrievedUser.getSurname(), newlyCreatedUser.getSurname());
			assertEquals(retrievedUser.getEmail(), newlyCreatedUser.getEmail());
			assertEquals(retrievedUser.getPassword(), newlyCreatedUser.getPassword());				
		} 
	}
	
	@Test
	void C_checkUserUpdate() {
		Optional<User> user = userMysqlRepository.findById(newlyCreatedUser.getId());
		
		assertThat(user.isPresent()).isTrue();
		
		if(user.isPresent()) {
			User retrievedUser = user.get();
			
			retrievedUser.setName(UPDATED_NAME);
			
			userMysqlRepository.save(retrievedUser);	
			
			user = userMysqlRepository.findById(newlyCreatedUser.getId());
			
			assertThat(user.isPresent()).isTrue();
			
			if(user.isPresent()) {
				User toBeCheckedUser = user.get();
				
				assertEquals(UPDATED_NAME, toBeCheckedUser.getName());
			}
		}	
	}
	
	@Test
	void D_checkUserDelete() {
		try {
			userMysqlRepository.deleteById(newlyCreatedUser.getId());
		} catch(IllegalArgumentException iae) {
			throw(iae);
		}
	}

}
