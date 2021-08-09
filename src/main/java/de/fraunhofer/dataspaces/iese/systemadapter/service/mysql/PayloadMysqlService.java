package de.fraunhofer.dataspaces.iese.systemadapter.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql.PayloadMysqlRepository;

@Service 
public class PayloadMysqlService {

	@Autowired
	private PayloadMysqlRepository payloadMysqlRepository;
	
	private List<Payload> reformatPayloadData(List<Payload> payloads) {
		
		for(Payload payload : payloads) {
			payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		}
		
		return payloads;	
	}
	
	public List<Payload> findAll() {
		return reformatPayloadData(payloadMysqlRepository.findAll());
	}
	
	public Optional<Payload> findById(int id) {
		
		Payload payload = payloadMysqlRepository.findById(id).get();
		
		payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		
		return Optional.of(payload);
	}
	
	public void save(Payload payload) {
		payloadMysqlRepository.save(payload);
	}
	
	public Payload saveAndReturn(Payload payload) {
		return payloadMysqlRepository.save(payload);
	}
	
	public void update(int id, Payload payload) {
		
		Optional<Payload> dbPayload = payloadMysqlRepository.findById(id);
		
		if(dbPayload.isPresent()) {
			Payload retrievedPayload = dbPayload.get();
			
			retrievedPayload.setHeaderId(payload.getHeaderId());
			retrievedPayload.setData(payload.getData());
			
			payloadMysqlRepository.save(retrievedPayload);
		}
	}
	
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
	
	public void deleteById(int id) {
		try {
			payloadMysqlRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
