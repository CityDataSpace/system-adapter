package de.fraunhofer.dataspaces.iese.systemadapter.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.fraunhofer.dataspaces.iese.systemadapter.entity.JSON;

@Repository
public class JSONDAO implements JSONDAOInterface {
	
	private EntityManager entityManager;
	
	@Autowired
	public JSONDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<JSON> findAll() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<JSON> query = session.createQuery("from JSON", JSON.class);
		
		List<JSON> jsonData = query.getResultList();
		
		return jsonData;
	}

	@Override
	public JSON findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		JSON query = session.get(JSON.class, id);
		
		return query;
	}

	@Override
	public void save(JSON json) {
		Session session = this.entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(json);
	}

	@Override
	public void deleteById(int id) {
		Session session = this.entityManager.unwrap(Session.class);
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("delete from json_data where id=:jsonId");
		
		query.setParameter("jsonId", id);
		
		query.executeUpdate();
	}

}
