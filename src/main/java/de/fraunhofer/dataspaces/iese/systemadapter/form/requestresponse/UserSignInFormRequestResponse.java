package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import java.util.ArrayList;


import org.springframework.stereotype.Service;


@Service
public class UserSignInFormRequestResponse{
	
	private boolean isLoggedIn;
	
	private int loggedInUserId;
	
	private ArrayList<String> violations;
	
	public UserSignInFormRequestResponse() {
		violations = new ArrayList<String>();
	}
	
	public UserSignInFormRequestResponse(boolean isLoggedIn, int loggedInUserId) {
		this.isLoggedIn = isLoggedIn;
		this.loggedInUserId = loggedInUserId;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public int getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(int loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public ArrayList<String> getViolations() {
		return violations;
	}
	
	public void setViolations(String violation) {
		violations.add(violation);
	}

	@Override
	public String toString() {
		return "UserSignInFormRequestResponse [isLoggedIn=" + isLoggedIn + ", loggedInUserId=" + loggedInUserId
				+ ", violations=" + violations + "]";
	}
}
