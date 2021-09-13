package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import java.util.ArrayList;


import org.springframework.stereotype.Service;


@Service
public class UserSignInFormRequestResponse{
	
	private boolean isLoggedIn;
	
	private ArrayList<String> violations;
	

	public UserSignInFormRequestResponse() {
		violations = new ArrayList<String>();
	}
	
	public UserSignInFormRequestResponse(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public ArrayList<String> getViolations() {
		return violations;
	}
	

	public void setViolations(String violation) {
		violations.add(violation);
	}

	@Override
	public String toString() {
		return "UserSignInFormRequestResponse [isLoggedIn=" + isLoggedIn + ", violations=" + violations + "]";
	}
	
	
}
