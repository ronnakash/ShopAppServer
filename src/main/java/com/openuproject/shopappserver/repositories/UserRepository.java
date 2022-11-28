/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.UserDto;
import entities.User;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author ronna
 */
public class UserRepository extends RepositoryAbst<User>  {
    TypedQuery<User> findByUsernameQuery;
    
    public UserRepository() {
        super("User", User.class);
        this.findByUsernameQuery = entityManager.createNamedQuery("User.findByUsername", User.class);
    }

    
    public EntityDto<User> getByUsername(String username){
        List<User> users =  findByUsernameQuery
                .setParameter("username", username)
                .getResultList();
        if (users.size() != 1)
            return null;
        return users.get(0).toDto();
    }
    
    public boolean isUsernameTaken(String username){
        return getByUsername(username) != null;
    }
    

    @Override
    public EntityDto<User> update(EntityDto<User> dto) {
        return ((UserDto) super.update(dto)).hidePassword();
    }

    
    
}
