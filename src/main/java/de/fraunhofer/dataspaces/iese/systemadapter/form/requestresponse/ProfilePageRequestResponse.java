package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import org.springframework.stereotype.Service;

/**
 * This class contains information consumed by ResponseEntity java class during ProfilePageRequest
 */
@Service
public class ProfilePageRequestResponse {
	
	private String name;
	
	private String surname;
	
	private String emailAddress;

	public ProfilePageRequestResponse() {
		
	}

	public ProfilePageRequestResponse(String name, String surname, String emailAddress) {
		this.name = name;
		this.surname = surname;
		this.emailAddress = emailAddress;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "ProfilePageRequestResponse [name=" + name + ", surname=" + surname + ", emailAddress=" + emailAddress
				+ "]";
	}
}
