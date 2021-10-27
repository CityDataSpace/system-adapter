package de.fraunhofer.dataspaces.iese.systemadapter.service.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.postgres.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.postgres.PayloadPostgresRepository;

/**
 * This class provides a service to Payload Postgres Repository
 */
@Service
public class PayloadPostgresService {
	
	@Autowired
	private PayloadPostgresRepository payloadPostgresRepository;
	
	/**
	 * This function takes a list of payloads and removes double quotations.
	 * @param payloads
	 * @return list of reformatted payloads
	 */
	private List<Payload> reformatPayloadData(List<Payload> payloads) {
		
		for(Payload payload : payloads) {
			payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		}
		
		return payloads;	
	}
	
	/**
	 * This function returns a list of payloads
	 * @return
	 */
	public List<Payload> findAll() {
		return reformatPayloadData(payloadPostgresRepository.findAll());
	}
	
	/**
	 * This function finds a payload object in postgres database
	 * @param id
	 * @return payload object
	 */
	public Optional<Payload> findById(int id) {
		
		Payload payload = payloadPostgresRepository.findById(id).get();
		
		payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		
		return Optional.of(payload);
	}
	
	/**
	 * This function saves a payload object to postgres database 
	 * @param payload
	 */
	public void save(Payload payload) {
		payloadPostgresRepository.save(payload);
	}
	
	/**
	 * This function saves a payload object to postgres database
	 * @param payload
	 * @return saved payload object
	 */
	public Payload saveAndReturn(Payload payload) {
		return payloadPostgresRepository.save(payload);
	}
	
	/**
	 * This function updates a payload entry in postgres database
	 * @param id
	 * @param payload
	 */
	public void update(int id, Payload payload) {
		
		Optional<Payload> dbPayload = payloadPostgresRepository.findById(id);
		
		if(dbPayload.isPresent()) {
			Payload retrievedPayload = dbPayload.get();
			
			retrievedPayload.setHeaderId(payload.getHeaderId());
			retrievedPayload.setData(payload.getData());
			
			payloadPostgresRepository.save(retrievedPayload);
		}
	}
	
	/**
	 * This function updates a payload entry in postgres database
	 * @param id
	 * @param payload
	 * @return updated payload object
	 */
	public Payload updateAndReturn(int id, Payload payload) {
		
		Optional<Payload> dbPayload = payloadPostgresRepository.findById(id);
		
		if(dbPayload.isPresent()) {
			Payload retrievedPayload = dbPayload.get();
			
			retrievedPayload.setHeaderId(payload.getHeaderId());
			retrievedPayload.setData(payload.getData());
			
			return payloadPostgresRepository.save(retrievedPayload);
		}
		
		return null;
	}
	
	/**
	 * This function deletes a payload object in postgres database
	 * @param id
	 */
	public void deleteById(int id) {
		try {
			payloadPostgresRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
