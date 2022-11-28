/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.ItemDto;
import entities.Category;
import entities.Item;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityTransaction;

/**
 *
 * @author ronna
 */
public class ItemRepository extends RepositoryAbst<Item>{
    
    public ItemRepository(){
        super("Item", Item.class);
    }
        
        
    @Override
    public EntityDto<Item> create(EntityDto<Item> dto) {
        ItemDto newItemDto = (ItemDto) super.create(dto);
        ((ItemDto) dto).setId(newItemDto.getId());
        return this.update(dto);
    }

    
    
    
    @Override
    public EntityDto<Item> update(EntityDto<Item> itemDto) {
        ItemDto dto = (ItemDto) itemDto;
        EntityTransaction t = entityManager.getTransaction();
        Item item = this.readLazy(dto.getId());
        t.begin();
        item.update(dto);
        List<Category> categories = dto
                .getCategories()
                .stream()
                .map(cdto -> entityManager.getReference(Category.class, cdto.getId()))
                .collect(Collectors.toList());
        item.setCategories(categories);
        entityManager.persist(item);
        t.commit();
        return item.toDto();
    }

    
}
