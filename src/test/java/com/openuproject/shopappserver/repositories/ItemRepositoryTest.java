/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.ItemDto;
import entities.Item;
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
public class ItemRepositoryTest {
    
    public ItemRepositoryTest() {
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

//    @org.junit.jupiter.api.Test
//    public void testFindAllItems() {
//        ItemRepository ir = new ItemRepository();
//        List<EntityDto<Item>> dtos = ir.readAll();
//        assertTrue(dtos.size() >= 0, "items num");
//    }
//    
//    @org.junit.jupiter.api.Test
//    public void testFindItem() {
//        ItemRepository ir = new ItemRepository();
//        ItemDto dto1 = (ItemDto) ir.read(1);
//        ItemDto dto2 = (ItemDto) ir.read(2);
//        assertTrue( dto1.getCategories().size()>=0 , "item categories num 1");
//        assertTrue(dto2.getCategories().size()>=0 , "item categories num 2");
//    }
    
    
    
}
