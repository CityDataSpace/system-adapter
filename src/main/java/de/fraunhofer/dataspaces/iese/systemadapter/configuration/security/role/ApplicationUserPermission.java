package de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.role;

/**
 * This enum contains user permissions
 */
public enum ApplicationUserPermission {
	
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write"),
	ADMIN_TRAINEE_READ("admintrainee:read"),
	ADMIN_TRAINEE_WRITE("admintrainee:write");
	
	private final String permission;
	
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}

}
