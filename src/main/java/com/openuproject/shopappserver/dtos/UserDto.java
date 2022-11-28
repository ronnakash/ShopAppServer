/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.dtos;

import entities.User;

/**
 *
 * @author ronna
 */
public class UserDto extends EntityDto<User>{
    
    private String username;
    private String firstName;
    private String lastName;
    private String permissions;
    private String password;
    private String picUrl;


        public UserDto(String username, String firstName, String lastName,
                Boolean permissions, Integer id, String picUrl) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissions = permissions? "Admin" : "User";
        this.picUrl = picUrl;
    }
    
    public UserDto(String username, String firstName, String lastName,
            Boolean permissions, Integer id, String password, String picUrl) {
        this(username, firstName, lastName, permissions, id, picUrl);
        this.password = password;
    }
    
    protected UserDto(Integer id){
        super(id);
    }
    
    public UserDto (User user) {
        this(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getPermissions(), user.getId(), user.getPassword(), user.getPicUrl());
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getPassword() {
        return password;
    }

    public String getPicUrl() {
        return picUrl;
    }
    
    public UserDto hidePassword(){
        password=null;
        return this;
    }

    @Override
    public User toEntity() {
        return new User(this);
    }

    
    
}


