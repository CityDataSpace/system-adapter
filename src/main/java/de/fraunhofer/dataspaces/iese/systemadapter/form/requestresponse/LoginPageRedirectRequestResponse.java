package de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse;

import org.springframework.stereotype.Service;

@Service
public class LoginPageRedirectRequestResponse {

	private boolean needsLogin;

	public LoginPageRedirectRequestResponse() {
		
	}

	public LoginPageRedirectRequestResponse(boolean needsLogin) {
		this.needsLogin = needsLogin;
	}

	public boolean isNeedsLogin() {
		return needsLogin;
	}

	public void setNeedsLogin(boolean needsLogin) {
		this.needsLogin = needsLogin;
	}

	@Override
	public String toString() {
		return "LoginPageRedirectRequestResponse [needsLogin=" + needsLogin + "]";
	}
	
}
