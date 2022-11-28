/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ronna
 */
public class PasswordCipherTest {
    
    public PasswordCipherTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of decrypt method, of class PasswordCipher.
     */
    @Test
    public void testEncryptDecrypt() {
        try {
            System.out.println("encrypt");
            String unencryptedString = "hi!";
            PasswordCipher instance = new PasswordCipher();
            String encrypted = instance.encrypt(unencryptedString);
            String result = instance.decrypt(encrypted);
            assertEquals(unencryptedString, result, "Uneven encryption");
        }
        catch (Exception e){
            fail("Exception thrown: " + e.getMessage());
        }
    }
    
}
