/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dtos.EntityDto;
import dtos.OrderDto;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import repositories.ShopEntityManagerFactory;
import javax.persistence.EntityManager;


/**
 *
 * @author ronna
 */
@Entity
@Table(name = "shoporder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopOrder.findAll", query = "SELECT s FROM ShopOrder s"),
    @NamedQuery(name = "ShopOrder.findByOrderTime", query = "SELECT s FROM ShopOrder s WHERE s.orderTime = :orderTime"),
    @NamedQuery(name = "ShopOrder.findByOrderUserId", query = "SELECT s FROM ShopOrder s WHERE s.orderUser.id = :orderUserId"),
    @NamedQuery(name = "ShopOrder.findById", query = "SELECT s FROM ShopOrder s WHERE s.id = :id")})
public class ShopOrder implements DatabaseEntity {

    
    @Column(name = "order_status")
    private Integer status;
    private static final long serialVersionUID = 1L;
    @Column(name = "order_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer id;
    @JoinColumn(name = "order_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User orderUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopOrder", fetch = FetchType.LAZY)
    private List<OrderedItems> orderedItems;

    public ShopOrder() {
    }

    public ShopOrder(Integer orderId) {
        this();
        this.id = orderId;
    }

    public ShopOrder(OrderDto dto) {
        this.status = dto.getStatus();
        EntityManager entityManager = ShopEntityManagerFactory.getEntityManager();
        this.orderUser = entityManager
                .getReference(User.class, dto.getUserId());
        long time = (dto.getTime() == null || dto.getTime() == 0 )? System.currentTimeMillis() : dto.getTime();
        this.orderTime = new Date(time);
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer orderId) {
        this.id = orderId;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    @XmlTransient
    public List<OrderedItems> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItemsList(List<OrderedItems> orderedItems) {
        this.orderedItems = orderedItems;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ShopOrder)) {
            return false;
        }
        ShopOrder other = (ShopOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ShopOrder[ orderId=" + id + " ]";
    }
    
    @Override
    public EntityDto toDto() {
        EntityDto dto = new OrderDto(this);
        return dto;
    }

    @Override
    public void update(EntityDto oDto) {
        OrderDto dto = (OrderDto) oDto;
        Integer dtoStatus = dto.getStatus();
        this.status = dtoStatus==null? this.status : dtoStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
