/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.UserDto;
import entities.User;
import java.util.List;
import javax.validation.constraints.*;
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
public class UserRepositoryTest {
    
    public UserRepositoryTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
//    
//
//    @org.junit.jupiter.api.Test
//    public void testFindAllUsers() {
//        UserRepository ur = new UserRepository();
//        List<EntityDto<User>> dtos = ur.readAll();
//        assertTrue(dtos.size() >= 2, "yay");
//    }
//    
//    @org.junit.jupiter.api.Test
//    public void testFindUser() {
//        UserRepository ur = new UserRepository();
//        UserDto dto = (UserDto) ur.read(1);
//        assertEquals("Ron", dto.getFirstName(), "yay");
//    }
    
    
}
