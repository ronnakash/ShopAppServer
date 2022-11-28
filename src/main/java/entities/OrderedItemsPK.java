/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ronna
 */
@Embeddable
public class OrderedItemsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "order_order_id", insertable = true, updatable = true)
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_item_id", insertable = true, updatable = true)
    private int itemId;


    public OrderedItemsPK() {
    }

    public OrderedItemsPK(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) itemId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderedItemsPK)) {
            return false;
        }
        OrderedItemsPK other = (OrderedItemsPK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderedItemsPK[ orderId=" + orderId + ", itemId=" + itemId + " ]";
    }
    
}
