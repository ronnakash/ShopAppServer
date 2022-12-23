/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entities.Category;
import entities.Item;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ronna
 */
public class ItemDto extends EntityDto<Item>{

    private String itemName;
    private String itemDescription;
    private BigDecimal itemBasePrice;
    private Integer itemDiscount;
    private BigDecimal itemFinalPrice;
    private List<CategoryDto> categories;
    private String picUrl;
    
    public ItemDto(Integer id, String itemName, String itemDescription, BigDecimal itemBasePrice,
            Integer itemDiscount, List<Category> categories, String picUrl){
        this(id, itemName, itemDescription, itemBasePrice, itemDiscount, picUrl);
        this.categories = categories==null? null : categories
                .stream()
                .map(category -> (CategoryDto) category.toDto())
                .collect(Collectors.toList());
    }
    
    private ItemDto(Integer id, String itemName, String itemDescription, BigDecimal itemBasePrice,
            Integer itemDiscount, String picUrl){
        super(id);
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemBasePrice = itemBasePrice;
        this.itemDiscount = itemDiscount;
        this.itemFinalPrice = itemBasePrice.multiply(new BigDecimal((100-itemDiscount)/100.0,
            new MathContext(2,RoundingMode.HALF_EVEN)))
                .setScale(2, RoundingMode.DOWN);
        
        this.picUrl = picUrl;
    }
    
    public ItemDto(Item item){
        this(item.getId(), item.getItemName(), item.getDescription(), item.getPrice(),
                item.getDiscount(), item.getCategories(), item.getPicUrl());
    }
    
    protected ItemDto(Integer id){
        super(id);
    }
    
    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public BigDecimal getItemBasePrice() {
        return itemBasePrice;
    }

    public Integer getItemDiscount() {
        return itemDiscount;
    }

    public BigDecimal getItemFinalPrice() {
        return itemFinalPrice;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public String getPicUrl() {
        return picUrl;
    }
        
    @Override
    public Item toEntity() {
        return new Item(this);
    }

    public static ItemDto ShallowCopy(Item item){
        return new ItemDto(item.getId(), item.getItemName(), item.getDescription(), item.getPrice(),
                item.getDiscount(), item.getPicUrl());
    }
    
    @Override
    public void setId(int id){
        super.setId(id);
    }
    
}
