package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody.UserSignInFormRequestBody;
import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.UserSignInFormRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

@RestController
@RequestMapping("/user/sign")
public class SignInController {
	
	@Autowired
	ValidatorFactory factory;
	
	@Autowired
	Validator validator;
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	
	public SignInController() {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@PostMapping("/signin")
	@CrossOrigin(origins = "http://localhost:3000/")
	public ResponseEntity<UserSignInFormRequestResponse> signInUser(@RequestBody UserSignInFormRequestBody request) {
		
		UserSignInFormRequestResponse userSignInFormRequestResponse = new UserSignInFormRequestResponse();
		
	    Set<ConstraintViolation<UserSignInFormRequestBody>> violations = validator.validate(request);
	    
	    if(violations.size() > 0) {
	    	for(ConstraintViolation<UserSignInFormRequestBody> cv : violations) {
	    		userSignInFormRequestResponse.setViolations(cv.getMessage());	
		    }
	    	userSignInFormRequestResponse.setLoggedIn(false);
			return ResponseEntity.ok(userSignInFormRequestResponse);
	    }
	    
	    User user = userMysqlService.findByEmailAndPassword(request.getEmailAddress(), request.getPassword());
	    
	    if(user == null) {
	    	userSignInFormRequestResponse.setViolations("Email Address and Password combination not correct. Try Again");
	    	userSignInFormRequestResponse.setLoggedIn(false);
			return ResponseEntity.ok(userSignInFormRequestResponse);
	    }
	        
	    userSignInFormRequestResponse.setLoggedIn(true);
		
		return ResponseEntity.ok(userSignInFormRequestResponse);
	        
	}
	
}
