package de.fraunhofer.dataspaces.iese.systemadapter.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.JSON;
import de.fraunhofer.dataspaces.iese.systemadapter.service.JSONService;

@RestController
@RequestMapping("/storage/database")
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
	
	@PostMapping("/")
	public JSON postNew(@RequestBody JSON json) {
		
		json.setId(0);
		
		service.save(json);
		
		return json;
	}
	
	@PutMapping("/")
	public JSON putNew(@RequestBody JSON json) {
		service.save(json);
		
		return json;
	}

	@DeleteMapping("/")
	public void delete(@RequestBody int id) {
		service.deleteById(id);
	}
}
