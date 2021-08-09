package de.fraunhofer.dataspaces.iese.systemadapter.service.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.model.postgres.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.postgres.PayloadPostgresRepository;

@Service
public class PayloadPostgresService {
	
	@Autowired
	private PayloadPostgresRepository payloadPostgresRepository;
	
	private List<Payload> reformatPayloadData(List<Payload> payloads) {
		
		for(Payload payload : payloads) {
			payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		}
		
		return payloads;	
	}
	
	public List<Payload> findAll() {
		return reformatPayloadData(payloadPostgresRepository.findAll());
	}
	
	public Optional<Payload> findById(int id) {
		
		Payload payload = payloadPostgresRepository.findById(id).get();
		
		payload.setData(payload.getData().replace("\"\"", "\"").replace("}\"", "}"));
		
		return Optional.of(payload);
	}
	
	public void save(Payload payload) {
		payloadPostgresRepository.save(payload);
	}
	
	public Payload saveAndReturn(Payload payload) {
		return payloadPostgresRepository.save(payload);
	}
	
	public void update(int id, Payload payload) {
		
		Optional<Payload> dbPayload = payloadPostgresRepository.findById(id);
		
		if(dbPayload.isPresent()) {
			Payload retrievedPayload = dbPayload.get();
			
			retrievedPayload.setHeaderId(payload.getHeaderId());
			retrievedPayload.setData(payload.getData());
			
			payloadPostgresRepository.save(retrievedPayload);
		}
	}
	
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
	
	public void deleteById(int id) {
		try {
			payloadPostgresRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
