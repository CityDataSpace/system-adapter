package de.fraunhofer.dataspaces.iese.systemadapter.form.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fraunhofer.dataspaces.iese.systemadapter.form.requestresponse.LoginPageRedirectRequestResponse;

@Controller
@RequestMapping("/")
public class LoginPageController {

	@GetMapping("loginPageRedirect")
	public ResponseEntity<LoginPageRedirectRequestResponse> loginPageRedirect() {
		return ResponseEntity.ok(new LoginPageRedirectRequestResponse(true));
	}
}
