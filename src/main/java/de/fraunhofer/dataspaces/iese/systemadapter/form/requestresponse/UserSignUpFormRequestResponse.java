package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

/**
 * This class contains information consumed by ResponseEntity java class during UserSignUpFormRequest
 */
@Service
public class UserSignUpFormRequestResponse {
	
	private boolean isUserSuccessfullyCreated;
	
	private ArrayList<String> violations;

	public UserSignUpFormRequestResponse() {
		violations = new ArrayList<String>();
	}

	public boolean isUserSuccessfullyCreated() {
		return isUserSuccessfullyCreated;
	}

	public void setUserSuccessfullyCreated(boolean isUserSuccessfullyCreated) {
		this.isUserSuccessfullyCreated = isUserSuccessfullyCreated;
	}

	public ArrayList<String> getViolations() {
		return violations;
	}
	

	public void setViolations(String violation) {
		violations.add(violation);
	}

	@Override
	public String toString() {
		return "UserSignUpFormRequestResponse [isUserSuccessfullyCreated=" + isUserSuccessfullyCreated + ", violations=" + violations + "]";
	}
	
}
