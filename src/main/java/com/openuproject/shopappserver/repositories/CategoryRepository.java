/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.CategoryDto;
import com.openuproject.shopappserver.dtos.EntityDto;
import entities.Category;
import entities.Item;

/**
 *
 * @author ronna
 */
public class CategoryRepository extends RepositoryAbst<Category>{
    
    public CategoryRepository(){
        super("Category", Category.class);
    }
        
    
}
