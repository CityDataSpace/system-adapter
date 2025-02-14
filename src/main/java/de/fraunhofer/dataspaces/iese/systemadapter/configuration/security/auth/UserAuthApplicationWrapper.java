package de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.auth;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class implements User Details interface in order to provide user information to Java Spring Security DAO
 */
public class UserAuthApplicationWrapper implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Set<? extends GrantedAuthority> grantedAuthorities;
	
	private final String password;
	
	private final String username;
	
	private final boolean isAccountNonExpired;
	
	private final boolean isAccountNonLocked;
	
	private final boolean isCredentialsNonExpired;
	
	private final boolean isEnabled;
	
	
	
	public UserAuthApplicationWrapper(Set<? extends GrantedAuthority> grantedAuthorities, String password,
			String username, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		super();
		this.grantedAuthorities = grantedAuthorities;
		this.password = password;
		this.username = username;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public String toString() {
		return "UserMysqlServiceAuthWrapper [grantedAuthorities=" + grantedAuthorities + ", password=" + password
				+ ", username=" + username + ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked="
				+ isAccountNonLocked + ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", isEnabled="
				+ isEnabled + "]";
	}

}
