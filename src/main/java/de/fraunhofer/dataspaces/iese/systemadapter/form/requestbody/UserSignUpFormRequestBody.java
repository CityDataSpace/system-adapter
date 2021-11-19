package de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.CapitalLetter;
import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.NotBlank;
import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.Size;

/**
 * This class contains information consumed by java request during UserSignUpFormRequest
 */
public class UserSignUpFormRequestBody {
	
	@NotBlank(message="Name should not be blank")
	@CapitalLetter(message="Name should start with capital letter")
	@Size(min=2, max=40, message="The length of name should be between 2 and 40 characters")
	private String name;
	
	@NotBlank(message="Surname should not be blank")
	@CapitalLetter(message="Surname should start with capital letter")
	@Size(min=2, max=40, message="The length of surname should be between 2 and 40 characters")
	private String surname;
	
	@NotBlank(message="E-mail Address should not be blank")
	@Size(min=5, max=50, message="The length of e-mail address should be between 5 and 50 characters")
	private String emailAddress;
	
	@NotBlank(message="Password should not be blank")
	@Size(min=8, max=100, message="The length of password should be between 8 and 100 characters ")
	private String password;
	
	public UserSignUpFormRequestBody() {
		super();
	}

	public UserSignUpFormRequestBody(String name, String surname, String emailAddress, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.emailAddress = emailAddress;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserSignOutFormRequestBody [name=" + name + ", surname=" + surname + ", emailAddress=" + emailAddress
				+ ", password=" + password + "]";
	}
	
}
