package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.dataspaces.iese.systemadapter.configuration.PersistanceMysqlConfiguration;
import de.fraunhofer.dataspaces.iese.systemadapter.data.FraunhoferDataSpace;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;


@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest(classes = PersistanceMysqlConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
public class PayloadMysqlRepositoryTest {
	
	@Autowired
	private PayloadMysqlRepository payloadMysqlRepository;
	
	private Payload payload;
	
	private Payload newlyCreatedPayload;
	
	private final UUID HEADER_ID = UUID.randomUUID();
	
	private final UUID UPDATED_HEADER_ID = UUID.randomUUID();
	
	private final String DATA;
	
	public PayloadMysqlRepositoryTest() throws JsonProcessingException {
		this.DATA = new ObjectMapper()
						.writeValueAsString(new FraunhoferDataSpace()
								.setName("Policy")
								.setType("Restriction")
								.setDuration("2 Days"));
	}
	

	@PostConstruct
	public void init() {
		payload = new Payload();
		
		payload.setHeaderId(HEADER_ID);
		payload.setData(DATA);
		
		newlyCreatedPayload = payloadMysqlRepository.save(payload);
	}
	
	@Test
	void A_checkPayloadCreate() {
		assertEquals(payload.getId(), newlyCreatedPayload.getId());
		assertEquals(HEADER_ID, newlyCreatedPayload.getHeaderId());
		assertEquals(DATA, newlyCreatedPayload.getData());
	}
	
	@Test 
	void B_checkPayloadRead() {
		Optional<Payload> payload = payloadMysqlRepository.findById(newlyCreatedPayload.getId());
		
		assertThat(payload.isPresent()).isTrue();
		
		if(payload.isPresent()) {
			Payload retrievedPayload = payload.get();
			
			assertEquals(retrievedPayload.getId(), newlyCreatedPayload.getId());
			assertEquals(retrievedPayload.getHeaderId(), newlyCreatedPayload.getHeaderId());
			assertEquals(retrievedPayload.getData().replace("\"\"", "\"").replace("}\"", "}"), newlyCreatedPayload.getData());
			
		}
	}
	
	@Test
	void C_checkPayloadUpdate() {
		Optional<Payload> payload = payloadMysqlRepository.findById(newlyCreatedPayload.getId());
		
		assertThat(payload.isPresent()).isTrue();
		
		if(payload.isPresent()) {
			Payload retrievedPayload = payload.get();
			
			retrievedPayload.setHeaderId(UPDATED_HEADER_ID);
			
			payloadMysqlRepository.save(retrievedPayload);	
			
			payload = payloadMysqlRepository.findById(newlyCreatedPayload.getId());
			
			assertThat(payload.isPresent()).isTrue();
			
			if(payload.isPresent()) {
				Payload toBeCheckedPayload = payload.get();
				
				assertEquals(UPDATED_HEADER_ID, toBeCheckedPayload.getHeaderId());
			}
		}	
	}
	
	@Test
	void D_checkPayloadDelete() {
		try {
			payloadMysqlRepository.deleteById(newlyCreatedPayload.getId());
		} catch(IllegalArgumentException iae) {
			throw(iae);
		}
	}
}
