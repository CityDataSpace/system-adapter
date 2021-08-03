package de.fraunhofer.dataspaces.iese.systemadapter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fraunhofer.dataspaces.iese.systemadapter.database.dummy.DummyDatabase;

@SpringBootApplication
public class SystemadapterApplication {
	
	@Autowired
	private DummyDatabase dummyDatabase;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SystemadapterApplication.class, args);
	}
	
	@PostConstruct
	public void init() throws JsonProcessingException {
		dummyDatabase.fillInDatabaseUser();
		dummyDatabase.fillInDatabasePayload();
	}

}
