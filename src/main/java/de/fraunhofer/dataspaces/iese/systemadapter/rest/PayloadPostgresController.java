package de.fraunhofer.dataspaces.iese.systemadapter.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.dataspaces.iese.systemadapter.model.postgres.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.service.postgres.PayloadPostgresService;

/**
 * This class is a controller exposing routes to access Postgres Database
 */
@RestController
@RequestMapping("/api/postgres")
public class PayloadPostgresController {

	@Autowired
	private PayloadPostgresService payloadPostgresService;
	
	@GetMapping("/payloads/findAll")
	public List<Payload> findAll() {
		return payloadPostgresService.findAll();
	}
	
	@GetMapping("/payloads/{payloadId}")
	public Optional<Payload> getPayloadById(@PathVariable int payloadId) {
		return payloadPostgresService.findById(payloadId);
	}
	
	@PostMapping("/payloads") 
	public Payload postPayload(@RequestBody Payload payload) {
		return payloadPostgresService.saveAndReturn(payload);
	}
	
	@PutMapping("/payloads/{payloadId}")
	public void putPayload(@PathVariable int payloadId, @RequestBody Payload payload) {
		payloadPostgresService.updateAndReturn(payloadId, payload);
	}
	
	@DeleteMapping("/payloads/{payloadId}")
	public void deletePayload(@PathVariable int payloadId) {
		payloadPostgresService.deleteById(payloadId);
	}
	
}
