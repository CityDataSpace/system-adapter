package de.fraunhofer.dataspaces.iese.systemadapter.database.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * This class contains entries for Mysql Database that correspond to dummy user.
 */
@Configuration
@PropertySource("classpath:credentials/dummy_credentials.properties")
public class DummyUserCredentials {
	
	@Autowired
	private Environment env;
	
	@Value("${user.name}")
	private String name;
	
	@Value("${user.surname}")
	private String surname;
	
	@Value("${user.username}")
	private String username;
	
	@Value("${user.password}")
	private String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void fillData() {
		this.name = env.getProperty("user.name");
		this.surname = env.getProperty("user.surname");
		this.username = env.getProperty("user.username");
		this.password = env.getProperty("user.password");
	}

	@Override
	public String toString() {
		return "DummyUserCredentials [name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + "]";
	}
		
}
