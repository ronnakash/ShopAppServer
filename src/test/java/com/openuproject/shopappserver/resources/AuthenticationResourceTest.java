/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AuthenticationDto;
import javax.ws.rs.core.Response;
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
public class AuthenticationResourceTest {
    
    public AuthenticationResourceTest() {
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
     * Test of login method, of class AuthenticationResource.
     */
    @Test
    public void testLogin() {
        System.out.println("putJson");
        AuthenticationResource instance = new AuthenticationResource();
//        UserRepository repo = new UserRepository();
//        List<Dto<User>> users = repo.readAll();
//        UserDto user = (UserDto) users.get(0);
        Gson gson = new GsonBuilder().create();
        String json = "{\"username\":\"Admin\",\"password\":\"123123\"}";
        Response response = instance.login(json);
        String dtoString = (String) response.getEntity();
        AuthenticationDto dto = gson.fromJson(dtoString, AuthenticationDto.class);
            assertTrue(dto.getMessage().equals("Logged in user Admin successfully"), "wrong message");
    }
    
}
