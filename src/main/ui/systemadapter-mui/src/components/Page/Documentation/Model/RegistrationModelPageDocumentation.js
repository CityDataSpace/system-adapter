import React from "react";

import Typography from '@material-ui/core/Typography';

import registration_model from './../../../../resources/images/models/registration_model.png'

const RegistrationModelPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        In the scope of Registration Model we have the following:
      </Typography>
      <img alt="" src={registration_model} style={{"width":"40%", "margin-left" : "20%"}} />
      <Typography paragraph>
        Registration Model registers different databases to the system, allowing to save data in different places.
      </Typography>
    </div>
  )
}

export default RegistrationModelPageDocumentation;