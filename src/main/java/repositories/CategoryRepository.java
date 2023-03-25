/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import entities.Category;

/**
 * @author ronna
 */
public class CategoryRepository extends RepositoryAbst<Category> {

	public CategoryRepository() {
		super("Category", Category.class);
	}


}
