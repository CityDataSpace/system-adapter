package de.fraunhofer.dataspaces.iese.systemadapter.service;

import java.util.List;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.Json;

public interface JsonService {
	
	public List<Json> findAll();
	
	public Json findById(int id);
	
	public void save(Json json);
	
	public void deleteById(int id);

}
