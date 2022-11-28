/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.dtos;

import entities.OrderedItems;
import entities.ShopOrder;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ronna
 */
public class OrderDto extends EntityDto<ShopOrder> {
    private Integer userId;
    private Long time;
    List<OrderedItemsDto> orderedItems;
    String status;

    public OrderDto(Integer id, Integer userId, Date time, List<OrderedItems> orderedItems, Integer status) {
        this(id, userId, time, status);    
        this.orderedItems = orderedItems
                .stream()
                .map(orderedItem -> new OrderedItemsDto(orderedItem))
                .collect(Collectors.toList());
    }

    public OrderDto(ShopOrder order){
        this(order.getId(), order.getOrderUser().getId(), order.getOrderTime(),
                order.getOrderedItems(), order.getStatus());
    }
    
    private OrderDto(Integer id, Integer userId, Date time,
            Integer status) {
        super(id);
        this.userId = userId;
        this.time = (time == null? new Date(System.currentTimeMillis()) : time).getTime();
        this.status = parseStatus(status);
    }
    
    private String parseStatus(int status){
        String statusStr;
        switch (status){
        case 0: {
            statusStr = "Pending";
           break;
        }
        case 1: {
            statusStr = "Shipped";
           break;
        }
        case 2: {
            statusStr = "Arrived";
           break;
        }
        case 3: {
            statusStr = "Cancelled";
           break;
        }
        default: throw new WebApplicationException("Failed parsing order status", 500);
        }      
        return statusStr;  
    }
    
    private OrderDto(Integer id, Integer userId, Date time, Integer status, List<OrderedItems> orderedItems){
        this(id, userId, time, status); 
        this.orderedItems = orderedItems
                .stream()
                .map(orderedItem -> OrderedItemsDto.shallowCopy(orderedItem))
                .collect(Collectors.toList());
    }

    
    public int getUserId() {
        return userId;
    }

    public Long getTime() {
        return time;
    }
    
    public List<OrderedItemsDto> getOrderedItems() {
        return orderedItems;
    }
    
    @Override
    public ShopOrder toEntity() {
        return new ShopOrder(this);
    }

    public Integer getStatus() {
        switch (status){
            case "Pending": return 0;
            case "Shipped": return 1;
            case "Arrived": return 2;
            case "Cancelled": return 3;
            default: throw new RuntimeException();
        }      
    }

    public static OrderDto shallowCopy(ShopOrder order) {
        return new OrderDto(order.getId(), order.getOrderUser().getId(), order.getOrderTime(),
                order.getStatus(), order.getOrderedItems());
    }

}
