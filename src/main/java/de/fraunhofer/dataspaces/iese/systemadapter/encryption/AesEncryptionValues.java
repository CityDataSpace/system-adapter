package de.fraunhofer.dataspaces.iese.systemadapter.encryption;

import java.security.Key;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

/**
 * This class implements AesEncryption by implementing methods from AttributeConverter interface
 * Note: only values of JSON input are encrypted, keys are not encrypted.
 */
public class AesEncryptionValues implements AttributeConverter<String, String> {
	
	private static final String AES = "AES";
	private static final byte[] encryptionKey = "this-is-test-key".getBytes();

	private final Cipher encryptCipher;
	private final Cipher decryptCipher;

	public AesEncryptionValues() throws Exception {
		Key key = new SecretKeySpec(encryptionKey, AES);
		encryptCipher = Cipher.getInstance(AES);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance(AES);
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			
			String[] split_key_values_comma = attribute.split(",");
			
			String encryptedString = "";
			
			for(int i = 0; i < split_key_values_comma.length; i++) {
				
				String[] split_key_values = split_key_values_comma[i].split(":");
				
				encryptedString += split_key_values[0] 
						+ ":\""
						+ Base64.getEncoder().encodeToString(encryptCipher.doFinal(split_key_values[1].getBytes())) 
						+ "\",";
			}
			
			return encryptedString.substring(0, encryptedString.length() - 1).concat("}");
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		
		try {
			
			String[] split_key_values_comma = dbData.split(",");
			
			String decryptedString = "";
			
			for(int i = 0; i < split_key_values_comma.length; i++) {
				
				String[] split_key_values = split_key_values_comma[i].split(":");
				
				decryptedString += split_key_values[0] 
						+ ":\""
						+ new String(
								decryptCipher.doFinal(
										Base64
											.getDecoder()
											.decode(split_key_values[1]
													.replace("\"", "")
													.replace("}", "")
													)
											)
								) 
						+ "\",";
			}
			
			
			
			return decryptedString.substring(0, decryptedString.length() - 1);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new IllegalArgumentException(e);
		}
		
		
	
	}

}
