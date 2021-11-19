package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import org.springframework.stereotype.Service;

/**
 * This class contains information consumed by ResponseEntity java class during RegistrationPagePostRequest
 */
@Service
public class RegistrationPagePostRequestResponse {
	
	private boolean isRegistered;

	public RegistrationPagePostRequestResponse() {
		
	}

	public RegistrationPagePostRequestResponse(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	@Override
	public String toString() {
		return "RegistrationPagePostRequestResponse [isRegistered=" + isRegistered + "]";
	}
	
}
