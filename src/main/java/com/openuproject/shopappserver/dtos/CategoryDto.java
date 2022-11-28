/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.dtos;

import entities.Category;
import entities.Item;
import java.util.List;

/**
 *
 * @author ronna
 */
public class CategoryDto extends EntityDto<Category>{
    private String categoryName;

    public CategoryDto(Integer id, String categoryName, List<Item> items){
        this(id, categoryName);
    }
    
    public CategoryDto(Category category){
        this(category.getId(), category.getCategoryName(), //category.getItemList()
                null);
    }
    
    protected CategoryDto(Integer id){
        super(id);
    }

    private CategoryDto(Integer id, String categoryName) {
        super(id);
        this.categoryName = categoryName;

    }
    
    public String getCategoryName() {
        return categoryName;
    }

    
    @Override
    public Category toEntity() {
        return new Category(this);
    }
    

    
}
