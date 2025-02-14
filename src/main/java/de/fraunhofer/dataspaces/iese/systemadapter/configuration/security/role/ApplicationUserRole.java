package de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.role;

import static de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.role.ApplicationUserPermission.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

/**
 * This enum contains user roles
 */
public enum ApplicationUserRole {
	
	STUDENT     (Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
	
	ADMIN       (Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, 
			                     ADMIN_TRAINEE_READ, ADMIN_TRAINEE_WRITE, 
			                     STUDENT_READ, STUDENT_WRITE)),
	
	ADMINTRAINEE(Sets.newHashSet(ADMIN_TRAINEE_READ, ADMIN_TRAINEE_WRITE, 
			                     STUDENT_WRITE, STUDENT_READ));
	
	private final Set<ApplicationUserPermission> permissions;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}

}
