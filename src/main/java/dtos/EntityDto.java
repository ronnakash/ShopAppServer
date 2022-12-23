/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entities.DatabaseEntity;


/**
 *
 * @author ronna
 */
public abstract class EntityDto<T extends DatabaseEntity> implements Dto {
    protected Integer id;
    
    protected EntityDto(Integer id){
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    protected void setId(int id){
        this.id = id;
    }
    
    public abstract T toEntity();
    
    
}
