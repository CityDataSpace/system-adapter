package de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody;


public class UserSignUpFormRequestBody {
	
	private String name;
	private String surname;
	private String emailAddress;
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
