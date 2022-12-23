/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dtos.OrderedItemsDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ronna
 */
@Entity
@Table(name = "ordereditems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderedItems.findAll", query = "SELECT o FROM OrderedItems o"),
    @NamedQuery(name = "OrderedItems.findByOrderId", query = "SELECT o FROM OrderedItems o WHERE o.orderedItemsPK.orderId = :orderId"),
    @NamedQuery(name = "OrderedItems.findByItemId", query = "SELECT o FROM OrderedItems o WHERE o.orderedItemsPK.itemId = :itemId"),
    @NamedQuery(name = "OrderedItems.findByQuantity", query = "SELECT o FROM OrderedItems o WHERE o.quantity = :quantity")})
public class OrderedItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderedItemsPK orderedItemsPK;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "item_item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item;
    @JoinColumn(name = "order_order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopOrder shopOrder;

    public OrderedItems() {
    }

    public OrderedItems(OrderedItemsPK orderedItemsPK) {
        this.orderedItemsPK = orderedItemsPK;
    }

    public OrderedItems(int orderOrderId, int itemItemId) {
        this.orderedItemsPK = new OrderedItemsPK(orderOrderId, itemItemId);
    }
    public OrderedItems(int orderId, int itemId, int quantity) {
        this.orderedItemsPK = new OrderedItemsPK(orderId, itemId);
        this.quantity = quantity;
    }
    
    public OrderedItems(OrderedItemsDto dto) {
        this.orderedItemsPK = new OrderedItemsPK(dto.getOrderId(), dto.getItem().getId());
        this.quantity = dto.getQuantity();
    }

    public OrderedItemsPK getOrderedItemsPK() {
        return orderedItemsPK;
    }

    public void setOrderedItemsPK(OrderedItemsPK orderedItemsPK) {
        this.orderedItemsPK = orderedItemsPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderedItemsPK != null ? orderedItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderedItems)) {
            return false;
        }
        OrderedItems other = (OrderedItems) object;
        if ((this.orderedItemsPK == null && other.orderedItemsPK != null) || (this.orderedItemsPK != null && !this.orderedItemsPK.equals(other.orderedItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderedItems[ orderedItemsPK=" + orderedItemsPK + " ]";
    }

    public OrderedItemsDto toDto() {
        OrderedItemsDto dto = new OrderedItemsDto(this);
        return dto;
    }
    
}
