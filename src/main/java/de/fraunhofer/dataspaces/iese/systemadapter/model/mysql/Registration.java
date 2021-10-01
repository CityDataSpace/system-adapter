package de.fraunhofer.dataspaces.iese.systemadapter.model.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="registrations")
@Table(schema="systemadapter")
public class Registration {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="registeredDatabases")
	private int registeredDatabases;
	
	@ManyToOne
	private User user;

	public Registration() {
		
	}

	public Registration(int id, int registeredDatabases, User user) {
		this.id = id;
		this.registeredDatabases = registeredDatabases;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRegisteredDatabases() {
		return registeredDatabases;
	}

	public void setRegisteredDatabases(int registeredDatabases) {
		this.registeredDatabases = registeredDatabases;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", registeredDatabases=" + registeredDatabases + ", user=" + user + "]";
	}
	
}
