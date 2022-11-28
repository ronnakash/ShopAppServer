/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.OrderDto;
import com.openuproject.shopappserver.dtos.UserDto;
import entities.ShopOrder;
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
public class OrderRepositoryTest {
    
    public OrderRepositoryTest() {
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
//
//    /**
//     * Test of findByUserId method, of class OrderRepository.
//     */
//    @Test
//    public void testFindByUserId() {
//        //System.out.println("findByUserId");
//        int userId = 1;
//        OrderRepository or = new OrderRepository();
//        List<OrderDto> dto = or.findByUserId(userId);
//        assertTrue(dto.size() >= 0, "find order");
//    }
//    
//    @org.junit.jupiter.api.Test
//    public void testFindAllOrders() {
//        OrderRepository or = new OrderRepository();
//        List<EntityDto<ShopOrder>> dtos = or.readAll();
//        assertTrue(dtos.size() >= 0, "orders num");
//    }
//    
//    @org.junit.jupiter.api.Test
//    public void testFindOrderById() {
//        OrderRepository or = new OrderRepository();
//        OrderDto dto = (OrderDto) or.read(2);
//        assertEquals(2, dto.getOrderedItems().size(), "ordered items num");
//        Integer iid = dto
//                .getOrderedItems()
//                .get(0)
//                .getItem()
//                .getId();
////        assertEquals(1, iid, "ordered item id");
//    }
//    

    
}
