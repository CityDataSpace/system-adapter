import React from "react";

import Typography from '@material-ui/core/Typography';

import role_model from './../../../../resources/images/models/role_model.png';

const RoleModelPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        In the scope of Role Model we have the following:
      </Typography>
      <img alt="" src={role_model} style={{"width":"40%", "margin-left" : "20%"}} />
      <Typography paragraph>
        This model support Role Based Authorization system that we build. Therefore, for each registered user there is a one or more
        corresponding entries in role table.
      </Typography>
    </div>
  )
}

export default RoleModelPageDocumentation;