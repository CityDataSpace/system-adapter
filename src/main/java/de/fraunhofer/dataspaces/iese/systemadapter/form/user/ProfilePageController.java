package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.ProfilePageRequestResponse;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.UserMysqlService;

@Controller
@RequestMapping("/")
public class ProfilePageController {
	
	@Autowired
	private UserMysqlService userMysqlService;

	@GetMapping("getuser/{userId}")
	public ResponseEntity<ProfilePageRequestResponse> loginPageRedirect(@PathVariable Integer userId) {
		
		User retrievedUser = userMysqlService.findById(userId).get();
		
		return ResponseEntity.ok(
				new ProfilePageRequestResponse(
						retrievedUser.getName(), 
						retrievedUser.getSurname(), 
						retrievedUser.getEmail()
						)
				);
	}
}
