package de.fraunhofer.dataspaces.iese.systemadapter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.Json;
import de.fraunhofer.dataspaces.iese.systemadapter.repository.JsonRepository;

@Service
public class JsonServiceImpl implements JsonService {
	
	private JsonRepository json;
	
	@Autowired
	public JsonServiceImpl(JsonRepository json) {
		this.json = json;
	}

	@Override
	public List<Json> findAll() {
		return this.json.findAll();
	}

	@Override
	public Json findById(int id) {
		
		Optional<Json> json = this.json.findById(id);
		
		if(json.isEmpty()) {
			return null;
		}
		
		return json.get();
	}

	@Override
	public void save(Json json) {
		this.json.save(json);
	}

	@Override

	public void deleteById(int id) {
		this.json.deleteById(id);
	}

}
