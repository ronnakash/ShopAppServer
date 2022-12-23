/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import dtos.EntityDto;
import dtos.UserDto;
import entities.User;
import java.util.List;

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
public class UserResourceTest {
    
    public UserResourceTest() {
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
     * Test of getJson method, of class UserResource.
     */
    @Test
    public void testGet() {
        System.out.println("getJson");
        UserResource instance = new UserResource();
        String json1 = (String) instance.getAll().getEntity();
        List<EntityDto<User>> result = instance.jsonToDtoList(json1);
        UserDto dto1 = (UserDto) result.get(0);
        String json2 = (String) instance.getOne(1).getEntity();
        UserDto dto2 = (UserDto) instance.jsonToDto(json2);
        assertTrue(result.size() > 2, "result length");
    }


    
}
