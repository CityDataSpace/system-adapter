import React from "react";

import Typography from '@material-ui/core/Typography';

import user_permissions from './../../../../resources/images/security/user_permissions.png';

import user_roles from './../../../../resources/images/security/user_roles.png';

const AuthorizationPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        Authorization is handled by Spring Security Package. In our case, we revolved our authorization in form of user roles and permissions. We defined user permissions
        like this:
      </Typography>
      <img alt="" src={user_permissions} style={{"width":"35%", "margin-left" : "25%"}} />
      <Typography paragraph>
        As we can see, we have in total 6 permissions. Their access methods were defined inside ApplicationUserPermission class. This way we can use
        permissions to allow or disallow certain actions. To embedd these decisions, we created user roles. The roles that we devised are:    
      </Typography>
      <img alt="" src={user_roles} style={{"width":"50%", "margin-left" : "25%"}} />
      <Typography paragraph>
        We defined three user roles in total. The routes that they can access are expressed with antMatchers function in scope of Authentication.
      </Typography>
    </div>
  )
}

export default AuthorizationPageDocumentation;