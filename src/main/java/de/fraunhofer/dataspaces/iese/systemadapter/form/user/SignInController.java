package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody.UserSignInFormRequestBody;
import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.UserSignInFormRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

@RestController
@RequestMapping("/user/sign")
public class SignInController {
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@Autowired
	private UserSignInFormRequestResponse userSignInFormRequestResponse;
	
	@PostMapping("/signin")
	@CrossOrigin(origins = "http://localhost:3000/")
	public UserSignInFormRequestResponse signInUser(@RequestBody UserSignInFormRequestBody request) {
		
		userSignInFormRequestResponse.setLoggedIn(true);
		
		return userSignInFormRequestResponse;
	}
	
}
