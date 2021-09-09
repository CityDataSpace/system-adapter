package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody.UserSignUpFormRequestBody;
import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.UserSignUpFormRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

@RestController
@RequestMapping("/user/sign")
public class SingUpController {
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@Autowired
	private UserSignUpFormRequestResponse userSignUpFormRequestResponse;
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:3000/")
	public UserSignUpFormRequestResponse signUpUser(@RequestBody UserSignUpFormRequestBody request) {
		
		userSignUpFormRequestResponse.setUserSuccessfullyCreated(true);
		
		return userSignUpFormRequestResponse;
	}

}
