package de.fraunhofer.dataspaces.iese.systemadapter.json;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.data.FraunhoferDataSpace;
import de.fraunhofer.dataspaces.iese.systemadapter.database.dummy.DummyDatabase;


@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
public class PayloadMysqlJsonConversionTest {
	
	@Autowired
	private DummyDatabase dummyDatabase;
	
	
	@Test
	public void A_ConvertJavaPojoToJson() {
		try {
			@SuppressWarnings("unused")
			String jsonString = new ObjectMapper()
					.writeValueAsString(new FraunhoferDataSpace()
							.setName("My Policy")
							.setType("Restriction")
							.setDuration("2 days"));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void B_ConvertJsonToJavaPojo() {
		
		try {
			Payload payload = dummyDatabase.fillInDatabasePayloadAndReturn();
			@SuppressWarnings("unused")
			FraunhoferDataSpace fds = new ObjectMapper().readValue(payload.getData(), FraunhoferDataSpace.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	

}
