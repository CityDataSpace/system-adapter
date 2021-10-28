package de.fraunhofer.dataspaces.iese.systemadapter.database.dummy;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.dataspaces.iese.systemadapter.data.FraunhoferDataSpace;
import de.fraunhofer.dataspaces.iese.systemadapter.model.postgres.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.service.postgres.PayloadPostgresService;

/**
 * This class contains entries for Postgres Database that are used throughout application. They do not represent real entries.
 */
@Component
@EnableTransactionManagement
public class DummyDatabasePostgres {
	
	@Autowired
	private PayloadPostgresService payloadPostgresService;
	

	public void fillInDatabasePayload() throws JsonProcessingException {
		Payload payload = new Payload();
		
		FraunhoferDataSpace fraunhoferDataSpace = new FraunhoferDataSpace();
		
		fraunhoferDataSpace
			.setName("My Policy")
			.setDuration("3 days")
			.setType("Restriction");
		
		payload.setHeaderId(UUID.randomUUID());
		payload.setData(new ObjectMapper().writeValueAsString(fraunhoferDataSpace));
		
		payloadPostgresService.save(payload);
	}
	
	public Payload fillInDatabasePayloadAndReturn() throws JsonProcessingException {
		Payload payload = new Payload();
		
		FraunhoferDataSpace fraunhoferDataSpace = new FraunhoferDataSpace();
		
		fraunhoferDataSpace
			.setName("My Policy")
			.setDuration("3 days")
			.setType("Allowance");
		
		payload.setHeaderId(UUID.randomUUID());
		payload.setData(new ObjectMapper().writeValueAsString(fraunhoferDataSpace));
		
		return payloadPostgresService.saveAndReturn(payload);
	}

}
