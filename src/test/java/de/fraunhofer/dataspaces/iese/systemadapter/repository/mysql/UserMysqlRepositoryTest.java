package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.configuration.PersistanceMysqlConfiguration;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest(classes = PersistanceMysqlConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
public class UserMysqlRepositoryTest {
	
	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	private User user;
	
	private User newlyCreatedUser;
	
	private final String NAME 	  = "Arian";
	private final String SURNAME  = "Ajdari";
	private final String EMAIL    = "arianajdari94@gmail.com";
	private final String PASSWORD = "new_password";
	
	public UserMysqlRepositoryTest() {
		
	}
	
	@PostConstruct
	public void init() {		
		user = new User();
		
		user.setName(NAME);
		user.setSurname(SURNAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		
		newlyCreatedUser = userMysqlRepository.save(user);		
	}
	
	@Test
	void A_checkCreatedUserValues() {
		assertEquals(user.getId(), newlyCreatedUser.getId());
		assertEquals(NAME, newlyCreatedUser.getName());
		assertEquals(SURNAME, newlyCreatedUser.getSurname());
		assertEquals(EMAIL, newlyCreatedUser.getEmail());
		assertEquals(PASSWORD, newlyCreatedUser.getPassword());		
		
		System.out.println("I am first");
	}
	
	@Test
	void B_checkCreatedUserDelete() {
		try {
			userMysqlRepository.deleteById(newlyCreatedUser.getId());
		} catch(IllegalArgumentException iae) {
			throw(iae);
		}
		
		System.out.println("I am second");
		
	}

}
