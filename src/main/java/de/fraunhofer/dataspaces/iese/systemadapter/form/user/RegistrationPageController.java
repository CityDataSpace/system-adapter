package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestbody.RegistrationPagePostRequestBody;
import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.RegistrationPageGetRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.RegistrationPagePostRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.RegistrationMysqlService;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

@Controller
@RequestMapping("/")
public class RegistrationPageController {
	
	@Autowired
	private RegistrationMysqlService registrationMysqlService;
	
	@Autowired
	private UserMysqlService userMysqlService;
	
	@GetMapping("registration/{userId}")
	public ResponseEntity<RegistrationPageGetRequestResponse> getRegistration(@PathVariable Integer userId) {
		
		List<Registration> retrievedRegistration = registrationMysqlService.findByUserId(userId);
		
		RegistrationPageGetRequestResponse registeredDatabases = new RegistrationPageGetRequestResponse();
		
		for(Registration registration : retrievedRegistration) {
			registeredDatabases.addRegistrations(registration.getRegisteredDatabases());
		}
		
		return ResponseEntity.ok(registeredDatabases);
	}
	
	@PostMapping("registration")
	public ResponseEntity<RegistrationPagePostRequestResponse> postRegistration(@RequestBody RegistrationPagePostRequestBody request) {
		
		Optional<User> user = userMysqlService.findById(request.getUserId());
		
		if(user.isPresent()) {
			User retrievedUser = user.get();
			
			Registration registration = new Registration();
			registration.setRegisteredDatabases(request.getDatabaseType());
			registration.setUser(retrievedUser);
			
			registrationMysqlService.save(registration);
			
			return ResponseEntity.ok(new RegistrationPagePostRequestResponse(true));
		}
		
		return ResponseEntity.ok(new RegistrationPagePostRequestResponse(false));
	}

}
