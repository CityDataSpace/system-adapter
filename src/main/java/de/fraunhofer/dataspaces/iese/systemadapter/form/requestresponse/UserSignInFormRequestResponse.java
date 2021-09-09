package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import org.springframework.stereotype.Service;

@Service
public class UserSignInFormRequestResponse {
	
	private boolean isLoggedIn;
	
	public UserSignInFormRequestResponse() {
		
	}
	
	public UserSignInFormRequestResponse(boolean isLoggedIn) {
		super();
		this.isLoggedIn = isLoggedIn;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public String toString() {
		return "UserSignInFormRequestResponse [isLoggedIn=" + isLoggedIn + "]";
	}
	
}
