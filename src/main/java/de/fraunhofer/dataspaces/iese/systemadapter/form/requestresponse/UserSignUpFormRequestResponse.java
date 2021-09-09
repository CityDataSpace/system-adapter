package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import org.springframework.stereotype.Service;

@Service
public class UserSignUpFormRequestResponse {
	
	private boolean isUserSuccessfullyCreated;

	public UserSignUpFormRequestResponse() {
		super();
	}

	public UserSignUpFormRequestResponse(boolean isUserSuccessfullyCreated) {
		super();
		this.isUserSuccessfullyCreated = isUserSuccessfullyCreated;
	}

	public boolean isUserSuccessfullyCreated() {
		return isUserSuccessfullyCreated;
	}

	public void setUserSuccessfullyCreated(boolean isUserSuccessfullyCreated) {
		this.isUserSuccessfullyCreated = isUserSuccessfullyCreated;
	}

	@Override
	public String toString() {
		return "UserSignUpFormRequestResponse [isUserSuccessfullyCreated=" + isUserSuccessfullyCreated + "]";
	}
	
}
