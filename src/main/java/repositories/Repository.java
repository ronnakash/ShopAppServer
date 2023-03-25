/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import dtos.EntityDto;
import entities.DatabaseEntity;

import java.util.List;

/**
 *
 * @author ronna
 */
public interface Repository<T extends DatabaseEntity> {
    
    List<EntityDto<T>> readAll();
    
    EntityDto<T> create(EntityDto<T> tDto);
    
    EntityDto<T> read(int id);
    
    EntityDto<T> update(EntityDto<T> tDto);
    
    EntityDto<T> delete(EntityDto<T> tDto);

    T readLazy(int id);
}
