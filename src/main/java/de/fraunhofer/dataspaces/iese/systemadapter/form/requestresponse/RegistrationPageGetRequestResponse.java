package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RegistrationPageGetRequestResponse {
	
	private List<Integer> registrations;

	public RegistrationPageGetRequestResponse() {
		registrations = new ArrayList<>();
		
	}

	public RegistrationPageGetRequestResponse(List<Integer> registrations) {
		this.registrations = registrations;
	}

	public List<Integer> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Integer> registrations) {
		this.registrations = registrations;
	}
	
	public void addRegistrations(Integer data) {
		registrations.add(data);
	}

	@Override
	public String toString() {
		return "RegistrationPageRequestResponse [registrations=" + registrations + "]";
	}

}
