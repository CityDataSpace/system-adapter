package de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody;


import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.NotBlank;
import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.Size;

public class UserSignInFormRequestBody {
	
	@NotBlank(message="E-mail Address should not be blank")
	@Size(min=5, max=50, message="The length of e-mail address should be between 5 and 50 characters")
	private String emailAddress;
	
	@NotBlank(message="Password should not be blank")
	@Size(min=8, max=100, message="The length of password should be between 8 and 100 characters")
	private String password;
	
	public UserSignInFormRequestBody() {
		
	}
	
	public UserSignInFormRequestBody(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password     = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
