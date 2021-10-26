import React from "react";

import Typography from '@material-ui/core/Typography';

const EncryptionPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        Since we are storing sensitive data, we decided to use Encryption as a measure to safely store data.
        We offer different forms of encryption data. In total we offer:
      </Typography>
      <Typography paragraph>
        <ul>
          <li>AES Encryption</li>
          <li>AES Encryption Values</li>
          <li>AES Encryption Values Secret Key</li>
          <li>RSA Encryption </li>
        </ul>
      </Typography>
      <Typography paragraph>
        In terms of security, AES encryption is the least secure, whereas RSA Encryption is the most secure. 
        We implemented all of these mechanisms using Converter Interface and Annotation from Java Spring.
      </Typography>
    </div>
  )
}

export default EncryptionPageDocumentation;