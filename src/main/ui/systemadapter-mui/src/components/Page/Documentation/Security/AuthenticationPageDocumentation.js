import React from "react";

import Typography from '@material-ui/core/Typography';

import cors_mapping from './../../../../resources/images/security/cors_mapping.png';
import secured_routes from './../../../../resources/images/security/secured_routes.png';
import user_auth_application_wrapper from './../../../../resources/images/security/user_auth_application_wrapper.png';

const AuthenticationPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        Authentication is handled completely by Spring Security package. Spring Security package
        brings the infrastructure for handling different forms of Authentication. Some of the Authentication 
        forms that Spring Security supports are: Basic, Login Form, OAuth, etc. In our case, we are using Basic Authentication.
        In the beginning, we secured our routes with these restrictions: 
      </Typography>
      <img alt="" src={secured_routes} style={{"width":"45%", "margin-left" : "20%"}} />
      <Typography paragraph>
        In the beginning, we disable csrf protection and make sure that for the requests that are presented inside antMatchers method to require authentication. Of course, we permit all
        users to access some content with permitAll method. After this step, we also allow CORS requests in this part:
      </Typography>
      <img alt="" src={cors_mapping} style={{"width":"40%", "margin-left" : "20%"}} />
      <Typography paragraph>
        We allow CORS Mappings by overriding addCorsMappings method and choosing which routes we want to map. In our case we allow for all.    
      </Typography>
      <Typography paragraph>
        After fixing the route, we defined UserAuthApplicationWrapper and UserAuthServiceWrapper in order to assist us to authenticate
        users. The UserAuthApplicationWrapper contains the following:
      </Typography>
      <img alt="" src={user_auth_application_wrapper} style={{"width":"50%", "margin-left" : "20%"}} />
      <Typography paragraph>
        We see that this is very similar to the User Model. However, the differences lie on the way UID and Roles are organized. Furthermore, this class
        implements UserDetails interface which is than consumed by Spring Security's DaoAuthenticationProvider class. 
      </Typography>
      <Typography paragraph>
        By providing all of this information, Spring Security can perform authentication. This way, are routes are secured and only
        authenticated users can access sensitive data.                
      </Typography>
    </div>
  )
}

export default AuthenticationPageDocumentation;