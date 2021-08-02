package de.fraunhofer.dataspaces.iese.systemadapter.database.dummy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.dataspaces.iese.systemadapter.data.FraunhoferDataSpace;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.PayloadMysqlRepository;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.UserMysqlRepository;

@Component
@EnableTransactionManagement
public class DummyDatabase {

	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	@Autowired
	private PayloadMysqlRepository payloadMysqlRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Transactional("userTransactionManager")
	public void fillInDatabaseUser() {
		User user = new User();
		
		user.setName("Arian");
		user.setSurname("Ajdari");
		user.setEmail("arianajdari94@gmail.com");
		user.setPassword(passwordEncoder.encode("my_strong_password"));
		
		userMysqlRepository.save(user);
	}
	
	@Transactional("userTransactionManager")
	public void fillInDatabasePayload() throws JsonProcessingException {
		Payload payload = new Payload();
		
		FraunhoferDataSpace fraunhoferDataSpace = new FraunhoferDataSpace();
		
		fraunhoferDataSpace
			.setName("My Policy2")
			.setDuration("2 days")
			.setType("Restriction");
		
		payload.setHeaderId(UUID.randomUUID());
		payload.setData(new ObjectMapper().writeValueAsString(fraunhoferDataSpace));
		
		payloadMysqlRepository.save(payload);
	}
}
