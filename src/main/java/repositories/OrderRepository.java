

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import dtos.EntityDto;
import dtos.OrderDto;
import entities.Item;
import entities.OrderedItems;
import entities.ShopOrder;
import exceptions.SubentityCreactionException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author ronna
 */
public class OrderRepository extends RepositoryAbst<ShopOrder> {
    TypedQuery<ShopOrder> findByOrderUserId;

    
    public OrderRepository(){
        super("ShopOrder", ShopOrder.class);
        this.findByOrderUserId = entityManager.createNamedQuery("ShopOrder.findByOrderUserId", super.type);
    } 
    
    //findByUser
    public List<OrderDto> findByUserId(int userId){
            List res = findByOrderUserId
                    .setParameter("orderUserId", userId)
                    .getResultList();
            return this.toDtos(res);
    }
    
    
    @Override
    public EntityDto<ShopOrder> update(EntityDto<ShopOrder> orderDto) {
        ShopOrder order = this.readLazy(orderDto.getId());
        order.setStatus(3);
        OrderDto dto = (OrderDto) orderDto;
        //create new order with details of old order
        ShopOrder updatedOrder = new ShopOrder(dto);
        EntityTransaction transaction1 = entityManager.getTransaction();
        transaction1.begin();
        entityManager.persist(updatedOrder);
        transaction1.commit();
        try{
            entityManager.refresh(updatedOrder);
            int uoid = updatedOrder.getId();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List updatedOrderedItems = dto
                    .getOrderedItems()
                    .stream()
                    .map(oid -> {
                        OrderedItems oi = new OrderedItems(
                            uoid,
                            oid.getItem().getId(),
                            oid.getQuantity());
                        oi.setItem(entityManager.getReference(Item.class, oid.getItem().getId()));
                        oi.setShopOrder(updatedOrder);
                        return oi;
                    })
                    .collect(Collectors.toList());
            updatedOrder.setOrderedItemsList(updatedOrderedItems);
            transaction.commit();
            entityManager.persist(updatedOrder);
            entityManager.persist(order);
            return new OrderDto(updatedOrder);
        }
        catch (Exception e){
            entityManager.remove(updatedOrder);
            throw new SubentityCreactionException("Failed creating ordered items" 
                    + " for update order request", e);
        }
    }

    public EntityDto<ShopOrder> updateStatus(EntityDto<ShopOrder> dto) {
        OrderDto updatedOrderDto = (OrderDto) dto;
        ShopOrder oldOrder = super.readLazy(updatedOrderDto.getId());
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        oldOrder.setStatus(updatedOrderDto.getStatus());
        entityManager.persist(oldOrder);
        t.commit();
        return oldOrder.toDto();
    }
    
    @Override
    public EntityDto<ShopOrder> create(EntityDto<ShopOrder> orderDto) {
        OrderDto dto = (OrderDto) orderDto;
        ShopOrder newOrder = new ShopOrder(dto);
        EntityTransaction transaction1 = entityManager.getTransaction();
        transaction1.begin();
        entityManager.persist(newOrder);
        transaction1.commit();
        try{
            entityManager.refresh(newOrder);
            int noid = newOrder.getId();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List orderedItems = dto
                    .getOrderedItems()
                    .stream()
                    .map(oid -> {
                        OrderedItems oi = new OrderedItems(
                            noid,
                            oid.getItem().getId(),
                            oid.getQuantity());
                        oi.setItem(entityManager.getReference(Item.class, oid.getItem().getId()));
                        oi.setShopOrder(newOrder);
                        return oi;
                    })
                    .collect(Collectors.toList());
            newOrder.setOrderedItemsList(orderedItems);
            transaction.commit();
            entityManager.persist(newOrder);
            return new OrderDto(newOrder);
        }
        catch(Exception e){
            throw new SubentityCreactionException(e);
        }
    }
    
}
