/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dtos.CategoryDto;
import dtos.EntityDto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author ronna
 */
@Entity
@Table(name = "category")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
		@NamedQuery(name = "Category.findByCategoryName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryName"),
		@NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id")})
public class Category implements DatabaseEntity {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "category_id")
	private Integer id;
	@Size(max = 100)
	@Column(name = "category_name")
	private String categoryName;
	@ManyToMany(mappedBy = "categories")
	private List<Item> itemList;

	public Category() {
	}

	public Category(Integer categoryId) {
		this.id = id;
	}

	public Category(CategoryDto dto) {
		this(dto.getId());
		this.categoryName = dto.getCategoryName();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Category)) {
			return false;
		}
		Category other = (Category) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

	@Override
	public String toString() {
		return "entities.Category[ id=" + id + " ]";
	}

	@Override
	public EntityDto toDto() {
		EntityDto dto = new CategoryDto(this);
		return dto;
	}

	@Override
	public void update(EntityDto dto) {
		CategoryDto cdto = (CategoryDto) dto;
		String name = cdto.getCategoryName();
		if (name != null)
			setCategoryName(name);
	}

}
