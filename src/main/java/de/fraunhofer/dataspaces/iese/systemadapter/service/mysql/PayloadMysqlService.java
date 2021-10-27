package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.PayloadMysqlRepository;

/**
 * This class provides a service to Payload Mysql Repository
 */
@Service 
public class PayloadMysqlService {

	@Autowired
	private PayloadMysqlRepository payloadMysqlRepository;
	
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
		return reformatPayloadData(payloadMysqlRepository.findAll());
	}
	
	/**
	 * This function finds a payload object in mysql database
	 * @param id
	 * @return payload object
	 */
	public Optional<Payload> findById(int id) {
		
		Payload payload = payloadMysqlRepository.findById(id).get();
		
		payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		
		return Optional.of(payload);
	}
	
	/**
	 * This function saves a payload object to mysql database 
	 * @param payload
	 */
	public void save(Payload payload) {
		payloadMysqlRepository.save(payload);
	}
	
	/**
	 * This function saves a payload object to mysql database
	 * @param payload
	 * @return saved payload object
	 */
	public Payload saveAndReturn(Payload payload) {
		return payloadMysqlRepository.save(payload);
	}
	
	/**
	 * This function updates a payload entry in mysql database
	 * @param id
	 * @param payload
	 */
	public void update(int id, Payload payload) {
		
		Optional<Payload> dbPayload = payloadMysqlRepository.findById(id);
		
		if(dbPayload.isPresent()) {
			Payload retrievedPayload = dbPayload.get();
			
			retrievedPayload.setHeaderId(payload.getHeaderId());
			retrievedPayload.setData(payload.getData());
			
			payloadMysqlRepository.save(retrievedPayload);
		}
	}
	
	/**
	 * This function updates a payload entry in mysql database
	 * @param id
	 * @param payload
	 * @return updated payload object
	 */
	public Payload updateAndReturn(int id, Payload payload) {
		
		Optional<Payload> dbPayload = payloadMysqlRepository.findById(id);
		
		if(dbPayload.isPresent()) {
			Payload retrievedPayload = dbPayload.get();
			
			retrievedPayload.setHeaderId(payload.getHeaderId());
			retrievedPayload.setData(payload.getData());
			
			return payloadMysqlRepository.save(retrievedPayload);
		}
		
		return null;
	}
	
	/**
	 * This function deletes a payload object in mysql database
	 * @param id
	 */
	public void deleteById(int id) {
		try {
			payloadMysqlRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
