package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

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
@SpringBootTest(classes = {PersistanceMysqlConfiguration.class, PayloadMysqlService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
public class PayloadMysqlServiceTest {
	
	@Autowired
	private PayloadMysqlService payloadMysqlService;
	
	private Payload payload;
	
	private Payload newlyCreatedPayload;
	
	private final UUID HEADER_ID = UUID.randomUUID();
	
	private final UUID UPDATED_HEADER_ID = UUID.randomUUID();
	
	private final String DATA;
	
	public PayloadMysqlServiceTest() throws JsonProcessingException {
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
		
		newlyCreatedPayload = payloadMysqlService.saveAndReturn(payload);
	}
	
	@Test
	void A_checkPayloadCreate() {
		assertEquals(payload.getId(), newlyCreatedPayload.getId());
		assertEquals(HEADER_ID, newlyCreatedPayload.getHeaderId());
		assertEquals(DATA, newlyCreatedPayload.getData());
	}
	
	@Test 
	void B_checkPayloadRead() {
		Optional<Payload> payload = payloadMysqlService.findById(newlyCreatedPayload.getId());
		
		assertThat(payload.isPresent()).isTrue();
		
		if(payload.isPresent()) {
			Payload retrievedPayload = payload.get();
			
			assertEquals(retrievedPayload.getId(), newlyCreatedPayload.getId());
			assertEquals(retrievedPayload.getHeaderId(), newlyCreatedPayload.getHeaderId());
			assertEquals(retrievedPayload.getData(), newlyCreatedPayload.getData());
			
		}
	}
	
	@Test
	void C_checkPayloadUpdate() {
		Optional<Payload> payload = payloadMysqlService.findById(newlyCreatedPayload.getId());
		
		assertThat(payload.isPresent()).isTrue();
		
		if(payload.isPresent()) {
			Payload retrievedPayload = payload.get();
			
			retrievedPayload.setHeaderId(UPDATED_HEADER_ID);
			
			payloadMysqlService.save(retrievedPayload);	
			
			payload = payloadMysqlService.findById(newlyCreatedPayload.getId());
			
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
			payloadMysqlService.deleteById(newlyCreatedPayload.getId());
		} catch(IllegalArgumentException iae) {
			throw(iae);
		}
	}
}
