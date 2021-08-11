package de.fraunhofer.dataspaces.iese.systemadapter.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.RandomStringUtils;

public class RsaEncryption implements AttributeConverter<String, String> {

	private static final String AES = "AES/CBC/PKCS5Padding";
	private static final String RSA = "RSA";

	private final Cipher encryptCipher;
	private final Cipher decryptCipher;
	
	private final Cipher encryptRSACipher;
	private final Cipher decryptRSACipher;
	
	public RsaEncryption() throws Exception {
		
		String randomPassword = RandomStringUtils.random(64, true, true);
		String randomSalt = RandomStringUtils.random(16, true, true);
		
		SecretKey secretKey = getKeyFromPassword(randomPassword, randomSalt);
		IvParameterSpec iv = generateIv();
		
		encryptCipher = Cipher.getInstance(AES);
		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		
		KeyPairGenerator gen2 = KeyPairGenerator.getInstance("RSA");
		gen2.initialize(1024);
		  
		KeyPair keyPair = gen2.genKeyPair();
		PrivateKey privatnyckel = keyPair.getPrivate();
		PublicKey publiknyckel = keyPair.getPublic();
		
		encryptRSACipher = Cipher.getInstance(RSA);
		encryptRSACipher.init(Cipher.ENCRYPT_MODE, publiknyckel);
		byte[] encryptedSecretKey = encryptRSACipher.doFinal(randomPassword.getBytes());
			
		decryptRSACipher = Cipher.getInstance(RSA);
		decryptRSACipher.init(Cipher.DECRYPT_MODE, privatnyckel);
		String decryptedSecretKey = new String(decryptRSACipher.doFinal(encryptedSecretKey));
			
		
		SecretKey secretKey2 = getKeyFromPassword(decryptedSecretKey, randomSalt);
		
		decryptCipher = Cipher.getInstance(AES);
		decryptCipher.init(Cipher.DECRYPT_MODE, secretKey2, iv);
	}
	
	public SecretKey getKeyFromPassword() 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	    char[] password = RandomStringUtils.random(256, true, true).toCharArray();
	    byte[] salt = RandomStringUtils.random(64, true, true).getBytes();
	    KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
	    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
	        .getEncoded(), "AES");
	    return secret;
	}
	
	public SecretKey getKeyFromPassword(String password, String salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
	    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
	        .getEncoded(), "AES");
	    return secret;
	}
	
	
	public IvParameterSpec generateIv() {
	    byte[] iv = new byte[16];
	    new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
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
