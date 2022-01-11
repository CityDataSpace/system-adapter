import { cssSettings } from "../../../DocumentationCssSettings/DocumentationCssSettingsWrapper";
import Typography from "@material-ui/core/Typography";

import user_model from "./../../../../resources/images/models/user_model.png";

const UserModelPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        Our User Model is constructed to be compatible with JPA Repository
        Interface. User Model looks like this:
      </Typography>
      <img alt="" src={user_model} style={cssSettings(50, 20)} />
      <Typography paragraph>
        As we see, we have standard accessor methods for name, surname, email
        and password fields. As for the other fields, they are used by Spring
        Security system. Finally, registration and roles fields are used by
        other tables in form of foreign keys.
      </Typography>
    </div>
  );
};

export default UserModelPageDocumentation;
