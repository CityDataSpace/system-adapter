import React from "react";

import Typography from '@material-ui/core/Typography';

const RsaEncryptionPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        AES Encryption is done by defining two instances of Cipher: one for encrypting, one for decrypting.
        In the beginning we convert the data into Base64 format and than feed it to encrypt Cipher. Before encryption itself takes place
        , we define a random generated secret key using RSA, which is fed to the Cipher. We only encrypt
        the values part, without encrypting the keys part of the data. After this step we save the data to database.         
      </Typography>
      <Typography paragraph>
        As far as decrypting is concerned, the data we received is decoded using Base64 decoder, than decrypted using decrypt Cipher and randomly generated secret key.         
      </Typography>
    </div>
  )
}

export default RsaEncryptionPageDocumentation;