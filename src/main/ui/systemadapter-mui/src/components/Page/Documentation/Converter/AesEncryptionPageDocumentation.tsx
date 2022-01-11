import Typography from "@material-ui/core/Typography";

const AesEncryptionPageDocumentation = () => {
  return (
    <div>
      <Typography paragraph>
        AES Encryption is done by defining two instances of Cipher: one for
        encrypting, one for decrypting. In the beginning we convert the data
        into Base64 format and than feed it to encrypt Cipher. After this step
        we save the data to database.
      </Typography>
      <Typography paragraph>
        As far as decrypting is concerned, the data we received is decoded using
        Base64 decoder and than decrypted using decrypt Cipher.
      </Typography>
    </div>
  );
};

export default AesEncryptionPageDocumentation;
