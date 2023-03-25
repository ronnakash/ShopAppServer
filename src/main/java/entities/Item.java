/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dtos.CategoryDto;
import dtos.EntityDto;
import dtos.ItemDto;
import repositories.ShopEntityManagerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ronna
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
		@NamedQuery(name = "Item.findByItemName", query = "SELECT i FROM Item i WHERE i.itemName = :itemName"),
		@NamedQuery(name = "Item.findByItemDescription", query = "SELECT i FROM Item i WHERE i.description = :description"),
		@NamedQuery(name = "Item.findByItemPrice", query = "SELECT i FROM Item i WHERE i.price = :price"),
		@NamedQuery(name = "Item.findByItemDiscount", query = "SELECT i FROM Item i WHERE i.discount = :discount"),
		@NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id")})
public class Item implements DatabaseEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "item_id")
	private Integer id;
	@Size(max = 100)
	@Column(name = "item_name")
	private String itemName;
	@Size(max = 255)
	@Column(name = "item_description")
	private String description;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "item_price")
	private BigDecimal price;
	@Column(name = "item_discount")
	private Integer discount;
	@Size(max = 200)
	@Column(name = "item_pic")
	private String picUrl;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "category_item", joinColumns = {
			@JoinColumn(name = "item_item_id", referencedColumnName = "item_id", insertable = true, updatable = true)}, inverseJoinColumns = {
			@JoinColumn(name = "category_category_id", referencedColumnName = "category_id", insertable = true, updatable = true)})
	private List<Category> categories;

	public Item() {
	}

	public Item(Integer id) {
		this.id = id;
	}

	public Item(ItemDto dto) {
		this.itemName = dto.getItemName();
		this.description = dto.getItemDescription();
		this.discount = dto.getItemDiscount();
		this.price = dto.getItemBasePrice();
		this.picUrl = dto.getPicUrl();
//        CategoryRepository repo = new CategoryRepository();
//        this.categories = dto.getCategories()
//                .stream()
//                .map(cdto -> {
//                    Category cat = repo.getEntityById(cdto.getId());
//                    return cat;
//                })
//                .collect(Collectors.toList());
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Item)) {
			return false;
		}
		Item other = (Item) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

	@Override
	public String toString() {
		return "entities.Item[ id=" + id + " ]";
	}

	@Override
	public EntityDto toDto() {
		EntityDto dto = new ItemDto(this);
		return dto;
	}

	@Override
	public void update(EntityDto iDto) {
		ItemDto dto = (ItemDto) iDto;
		String itemName = dto.getItemName();
		String description = dto.getItemDescription();
		Integer discount = dto.getItemDiscount();
		BigDecimal price = dto.getItemBasePrice();
		String picUrl = dto.getPicUrl();
		List<CategoryDto> categories = dto.getCategories();
		EntityManager entityManager = ShopEntityManagerFactory.getEntityManager();
		this.itemName = itemName == null ? this.itemName : itemName;
		this.description = description == null ? this.description : description;
		this.discount = discount == null ? this.discount : discount;
		this.price = price == null ? this.price : price;
		this.picUrl = picUrl == null ? this.picUrl : picUrl;
//        this.categories = categories == null ? this.categories : 
//            categories
//                .stream()
//                .map(cdto -> entityManager.getReference(Category.class, cdto.getId()))
//                .collect(Collectors.toList());
	}


}
