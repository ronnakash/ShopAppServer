/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import exceptions.PasswordChiperException;
import io.github.cdimascio.dotenv.Dotenv;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.WebApplicationException;
import org.apache.commons.codec.binary.Base64;


public class PasswordCipher {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "AES";
    private KeySpec ks;
    private final Dotenv dotenv = Dotenv
                                    .configure()
                                    //.directory("./src/main/java/assets")
                                    .filename(".env")
                                    .load();
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public PasswordCipher() {
        try{
            myEncryptionKey = dotenv.get("ENCRYPTION_SECRET");
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = new SecretKeySpec(arrayBytes, 0, arrayBytes.length, "AES");
        }
        catch (Exception e) {
            throw new PasswordChiperException("Encrypting password failed", e);
        }

    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            throw new PasswordChiperException("Encrypting password failed", e);
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            throw new PasswordChiperException("Decrypting password failed", e);
        }
        return decryptedText;
    }


}
