package de.fraunhofer.dataspaces.iese.systemadapter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fraunhofer.dataspaces.iese.systemadapter.dao.JSONDAO;
import de.fraunhofer.dataspaces.iese.systemadapter.entity.JSON;

@Service
public class JSONService implements JSONServiceInterface {
	
	private JSONDAO json;
	
	@Autowired
	public JSONService(JSONDAO json) {
		this.json = json;
	}

	@Override
	@Transactional
	public List<JSON> findAll() {
		return this.json.findAll();
	}

	@Override
	@Transactional
	public JSON findById(int id) {
		return this.json.findById(id);
	}

	@Override
	@Transactional
	public void save(JSON json) {
		this.json.save(json);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		this.json.deleteById(id);
	}

}
