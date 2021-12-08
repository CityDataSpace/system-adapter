import { cssSettings } from "../../../DocumentationCssSettings/DocumentationCssSettingsWrapper";

import Typography from '@material-ui/core/Typography';

import payload_model from './../../../../resources/images/models/payload_model.png'

const PayloadModelPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        In the scope of Payload Model we have the following:
      </Typography>
      <img alt="" src={payload_model} style={cssSettings(40, 20)} />
      <Typography paragraph>
        Payload Model is used to handle sensitive data that is stored outside Fraunhofer Data Space. Most importantly,
        data field is created using a converter. This converter does the encryption and handling of the data.
      </Typography>
    </div>
  )
}

export default PayloadModelPageDocumentation;