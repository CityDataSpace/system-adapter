package de.fraunhofer.dataspaces.iese.systemadapter.dao;

import java.util.List;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.JSON;

public interface JSONDAOInterface {

	public List<JSON> findAll();
	
	public JSON findById(int id);
	
	public void save(JSON json);
	
	public void deleteById(int id);
}
