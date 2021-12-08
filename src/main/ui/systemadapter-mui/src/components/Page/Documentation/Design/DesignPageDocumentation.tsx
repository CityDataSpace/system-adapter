import Typography from "@material-ui/core/Typography";

import highest_abstraction_level from "./../../../../resources/images/design/highest_abstraction_level.png";
import system_adapter from "./../../../../resources/images/design/system_adapter.png";
import main_aspects from "./../../../../resources/images/design/main_aspects.png";

const DesignPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        The main idea of System Adapter is to allow users a secure way of
        storing data outside of Fraunhofer Data Space Ecosystem. To achieve
        this, we offer different databases with the possibility of Encryption.
        At the highest abstraction layer, System Adapter looks like:
      </Typography>
      <img alt="" src={highest_abstraction_level} style={{ width: "100%" }} />
      <Typography paragraph>
        On the left side we have Fraunhofer Data Space with its infrastructure,
        whereas on the right side we have System Adapter. Users, by connecting
        to System Adapter through HTTP connection can conduct their
        transactions. System Adapter is made out:
      </Typography>
      <img alt="" src={system_adapter} style={{ width: "100%" }} />
      <Typography paragraph>
        System Adapter is made from Front-End and Back-End. Front-End is handled
        by using REACT bundled up with Material UI, whereas Back-End is handled
        by Java Spring. Connections to the database are handled by JPA.
      </Typography>
      <Typography paragraph>
        One of the main objectives of Fraunhofer Data Space is to ensure data
        safety. There we designed our security around these three elements:
      </Typography>
      <img alt="" src={main_aspects} style={{ width: "100%" }} />
    </div>
  );
};

export default DesignPageDocumentation;
