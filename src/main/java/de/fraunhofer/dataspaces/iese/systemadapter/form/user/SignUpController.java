package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody.UserSignUpFormRequestBody;
import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.UserSignUpFormRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

@RestController
@RequestMapping("/user/sign")
public class SignUpController {
	
	@Autowired
	ValidatorFactory factory;
	
	@Autowired
	Validator validator;
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	public SignUpController() {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:3000/")
	public ResponseEntity<UserSignUpFormRequestResponse> signUpUser(@RequestBody UserSignUpFormRequestBody request) {
		
		UserSignUpFormRequestResponse userSignUpFormRequestResponse = new UserSignUpFormRequestResponse();
		
	    Set<ConstraintViolation<UserSignUpFormRequestBody>> violations = validator.validate(request);
	    
	    if(violations.size() > 0) {
	    	for(ConstraintViolation<UserSignUpFormRequestBody> cv : violations) {
	    		userSignUpFormRequestResponse.setViolations(cv.getMessage());
	    	}
	    	userSignUpFormRequestResponse.setUserSuccessfullyCreated(false);
	    	return ResponseEntity.ok(userSignUpFormRequestResponse);
	    }
	    
	    if(userMysqlService.isUserAlreadyRegistered(request.getEmailAddress())) {
	    	userSignUpFormRequestResponse.setViolations("User with provided e-mail address already registered. Please log in");
	    	userSignUpFormRequestResponse.setUserSuccessfullyCreated(false);
	    	return ResponseEntity.ok(userSignUpFormRequestResponse);
	    }
	    
	    User user = new User();
	    
	    user.setName(request.getName());
	    user.setSurname(request.getSurname());
	    user.setEmail(request.getEmailAddress());
	    user.setPassword(request.getPassword());
	    
	    Optional<User> newlyCreatedUser = Optional.of(userMysqlService.saveAndReturn(user));
	    
	    if(newlyCreatedUser.isEmpty()) {
	    	userSignUpFormRequestResponse.setViolations("User was not saved to Database");
	    	userSignUpFormRequestResponse.setUserSuccessfullyCreated(false);
		    return ResponseEntity.ok(userSignUpFormRequestResponse);	
	    }
	    
	    userSignUpFormRequestResponse.setUserSuccessfullyCreated(true);
	    
	    return ResponseEntity.ok(userSignUpFormRequestResponse);	
	}
}
