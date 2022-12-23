/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dtos.EntityDto;
import dtos.UserDto;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import util.PasswordCipher;

/**
 *
 * @author ronna
 */
@Entity
@Table(name = "appuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByPermissions", query = "SELECT u FROM User u WHERE u.permissions = :permissions"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")})
public class User implements DatabaseEntity {

    private static final long serialVersionUID = 1L;
    @Size(max = 32)
    @Column(name = "username")
    private String username;
    @Size(max = 32)
    @Column(name = "user_password")
    private String password;
    @Size(max = 20)
    @Column(name = "user_first_name")
    private String firstName;
    @Size(max = 20)
    @Column(name = "user_last_name")
    private String lastName;
    @Column(name = "user_permissions")
    private Boolean permissions;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderUser", fetch = FetchType.LAZY)
    private List<ShopOrder> shopOrderList;
    
    @Size(max = 200)
    @Column(name = "pic_url")
    private String picUrl;


    
    public User() {
        
    }

    public User(Integer userId) {
        this.id = userId;
    }

    public User(UserDto dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.username = dto.getUsername();
        this.permissions = dto.getPermissions()=="Admin"? true : false;
        this.password = this.encryptPassword(dto.getPassword());
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getPermissions() {
        return permissions;
    }

    public void setPermissions(Boolean permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer userId) {
        this.id = userId;
    }

    @XmlTransient
    public List<ShopOrder> getShopOrderList() {
        return shopOrderList;
    }

    public void setShopOrderCollection(List<ShopOrder> shopOrderList) {
        this.shopOrderList = shopOrderList;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ userId=" + id + " ]";
    }
    
    @Override
    public EntityDto toDto() {
        EntityDto dto = new UserDto(this);
        return dto;
    }

    @Override
    public void update(EntityDto userDto) {
        UserDto dto = (UserDto) userDto;
        this.firstName = dto.getFirstName()==null? this.firstName : dto.getFirstName();
        this.lastName = dto.getLastName()==null? this.lastName : dto.getLastName();
        this.username = dto.getUsername()==null? this.username : dto.getUsername();
        this.permissions = dto.getPermissions()=="Admin"? true : this.permissions;
        this.picUrl = dto.getPicUrl() == null? this.picUrl : dto.getPicUrl();
        this.password = dto.getPassword()==null?
                this.password : this.encryptPassword(dto.getPassword());
    }

    private String encryptPassword(String password) {
        try {
            return new PasswordCipher().encrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "Failed to encrypt password", ex);
        }
        return null;
    }
    
    protected String getDecryptedPassword() {
        try {
            return new PasswordCipher().decrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "Failed to decrypt password", ex);
        }
        return null;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    

}
