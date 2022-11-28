/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openuproject.shopappserver.dtos.AuthenticationDto;
import com.openuproject.shopappserver.dtos.UserDto;
import com.openuproject.shopappserver.repositories.UserRepository;
import exceptions.ShopApplicationException;
import exceptions.UnhandledExceptionMapper;
import exceptions.UsernameTakenException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.JwtManager;
import util.PasswordCipher;

/**
 * REST Web Service
 *
 * @author ronna
 */
@Path("/auth")
public class AuthenticationResource {
    Gson gson;
    UserRepository repository;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AuthenticationResource
     */
    public AuthenticationResource() {
        gson = new GsonBuilder().create();
        repository = new UserRepository();
    }

     //login
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(String content) {
        try {
            UserDto contentDto = gson.fromJson(content, UserDto.class);
            UserDto user = (UserDto) ((UserRepository)repository).getByUsername(contentDto.getUsername());
            if (user == null)
                throw new ShopApplicationException("Wrong credentials provided", 400) {};
            JwtManager jwtm = new JwtManager();
            PasswordCipher pc = new PasswordCipher();
            String decryptedPassword = pc.decrypt(user.getPassword());
            if (!decryptedPassword.equals(contentDto.getPassword())
                    || !user.getUsername().equals(contentDto.getUsername()))
                return Response.status(400).entity("Wrong credentials provided").build();
            String jwt = jwtm.createUserJWT(user);
            AuthenticationDto dto = new AuthenticationDto(user.hidePassword(), jwt);
            String json = gson.toJson(dto, AuthenticationDto.class);
            return Response.status(200).entity(json).build();
        }
        catch (ShopApplicationException e) {
            return e.getResponse();
        }
        catch (Exception e) {
//            if (e instanceof ShopApplicationException)
//                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }

    
    //register
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(String content) {
        try {
            UserDto contentDto = gson.fromJson(content, UserDto.class);
            if (repository.isUsernameTaken(contentDto.getUsername()))
                throw new UsernameTakenException("Username " + contentDto.getUsername() 
                        + " is taken");
            UserDto user = (UserDto) repository.create(contentDto);
            AuthenticationDto dto = new AuthenticationDto(user);
            String json = gson.toJson(dto, AuthenticationDto.class);
            return Response.status(201).entity(json).build(); 
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
}
