package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.RegistrationPageRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.RegistrationMysqlService;

@Controller
@RequestMapping("/")
public class RegistrationPageController {
	
	@Autowired
	private RegistrationMysqlService registrationMysqlService;
	
	@GetMapping("getregistration/{userId}")
	public ResponseEntity<RegistrationPageRequestResponse> getRegistration(@PathVariable Integer userId) {
		
		List<Registration> retrievedRegistration = registrationMysqlService.findByUserId(userId);
		
		RegistrationPageRequestResponse registeredDatabases = new RegistrationPageRequestResponse();
		
		for(Registration registration : retrievedRegistration) {
			registeredDatabases.addRegistrations(registration.getRegisteredDatabases());
		}
		
		return ResponseEntity.ok(registeredDatabases);
	}

}
