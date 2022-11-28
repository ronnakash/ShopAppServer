/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import entities.DatabaseEntity;
import java.util.List;

/**
 *
 * @author ronna
 */
public interface Repository<T extends DatabaseEntity> {
    
    public List<EntityDto<T>> readAll();
    
    public EntityDto<T> create(EntityDto<T> tDto);
    
    public EntityDto<T> read(int id);
    
    public EntityDto<T> update(EntityDto<T> tDto);
    
    public EntityDto<T> delete(EntityDto<T> tDto);

    T readLazy(int id);
}
