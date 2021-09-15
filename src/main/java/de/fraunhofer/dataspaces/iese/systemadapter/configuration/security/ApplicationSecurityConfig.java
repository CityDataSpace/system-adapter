package de.fraunhofer.dataspaces.iese.systemadapter.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	
	private final UserAuthServiceWrapper userAuthServiceWrapper;
	
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserAuthServiceWrapper userAuthServiceWrapper) {
		this.passwordEncoder = passwordEncoder;
		this.userAuthServiceWrapper = userAuthServiceWrapper;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			//.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and() 
			.formLogin();
	}
	
	/*
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails arian = User.builder()
				.username("arian")
				.password(passwordEncoder.encode("password"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(arian);
	}
	
	*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userAuthServiceWrapper);
		return provider;
	}
}
