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

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.PayloadMysqlService;

/**
 * This class is a controller exposing routes to access Mysql Database
 */
@RestController
@RequestMapping("/api/mysql")
public class PayloadMysqlController {
	
	@Autowired
	private PayloadMysqlService payloadMysqlService;
	
	@GetMapping("/payloads/findAll")
	public List<Payload> findAll() {
		return payloadMysqlService.findAll();
	}
	
	@GetMapping("/payloads/{payloadId}")
	public Optional<Payload> getPayloadById(@PathVariable int payloadId) {
		return payloadMysqlService.findById(payloadId);
	}
	
	@PostMapping("/payloads")
	public Payload postPayload(@RequestBody Payload payload) {
		return payloadMysqlService.saveAndReturn(payload);
	}
	
	@PutMapping("/payloads/{payloadId}")
	public Payload updatePayload(@PathVariable int payloadId, @RequestBody Payload payload) {
		return payloadMysqlService.updateAndReturn(payloadId, payload);
	}
	
	@DeleteMapping("/payloads/{payloadId}")
	public void deletePayloadById(@PathVariable int payloadId) {
		payloadMysqlService.deleteById(payloadId);
	}

}
