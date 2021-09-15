package de.fraunhofer.dataspaces.iese.systemadapter.hashing;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BCrypt {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
        DelegatingPasswordEncoder delPasswordEncoder=  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    BCryptPasswordEncoder bcryptPasswordEncoder =new BCryptPasswordEncoder(10);
	    delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder);
	    return delPasswordEncoder;      
	}
}
