package de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody;


public class RegistrationPagePostRequestBody {

	private int databaseType;
	
	private int userId;

	public RegistrationPagePostRequestBody() {
		
	}

	public RegistrationPagePostRequestBody(int databaseType, int userId) {
		this.databaseType = databaseType;
		this.userId = userId;
	}

	public int getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "RegistrationPagePostRequestBody [databaseType=" + databaseType + ", userId=" + userId + "]";
	}
	
}
