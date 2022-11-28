/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package util;

import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.Map;
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
public class JwtManagerTest {
    
    public JwtManagerTest() {
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
     * Test of createJWT method, of class JwtManager.
     */
    @Test
    public void testCreateandDecodeJWT() {
        System.out.println("createJWT");
        String id = "1";
        String issuer = "me";
        String subject = "u";
        long ttlMillis = 10000L;
        JwtManager instance = new JwtManager();
        Map m = new HashMap<String, Object>();
        m.put("username", "ronna");
        m.put("permissions", "admin");
        String result = instance.createJWT(id, issuer, subject, ttlMillis, m);
        Claims c = instance.decodeJWT(result);
        assertEquals(id, c.getId(), "id");
        assertEquals(issuer, c.getIssuer(), "issuer");
        assertEquals(subject, c.getSubject(), "subject");
        assertEquals(c.get("username", String.class), "ronna", "issuer");
        assertEquals("admin", c.get("permissions", String.class), "subject");

    }


}
