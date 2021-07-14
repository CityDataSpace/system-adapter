package de.fraunhofer.dataspaces.iese.systemadapter.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.JSON;
import de.fraunhofer.dataspaces.iese.systemadapter.service.JSONService;

@RestController
@RequestMapping("/storage")
public class JSONDBController {
	
	JSONService service;
	
	@Autowired
	public JSONDBController(JSONService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public List<JSON> getAll() {
		return service.findAll();
	}
	
	@GetMapping("/{json_id}") 
	public JSON getById(@PathVariable int json_id) {
		return service.findById(json_id);
	}

}
