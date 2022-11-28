/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import annotations.PermissionsRequired;
import com.google.gson.Gson;
import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.UserDto;
import com.openuproject.shopappserver.repositories.UserRepository;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import com.google.gson.reflect.TypeToken;
import com.openuproject.shopappserver.dtos.AuthenticationDto;
import exceptions.AuthorizationException;
import exceptions.ShopApplicationException;
import exceptions.UnhandledExceptionMapper;
import exceptions.UsernameTakenException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.JwtManager;
import util.PasswordCipher;

/**
 * REST Web Service
 *
 * @author ronna
 */
@Path("/user")
public class UserResource extends ResourceAbst<User> {
  
    
    public UserResource() {
        super(new UserRepository(), UserDto.class, new TypeToken<List<UserDto>>() {}.getType());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin"})
    @Override
    public Response getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin"})
    public Response getOne(@PathParam("id") int id) {
        return super.getOne(id);
    }

        
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin"})
    public Response createOne(String content) {
        UserDto contentDto = (UserDto) this.jsonToDto(content);
        if (((UserRepository) repository).isUsernameTaken(contentDto.getUsername()))
            return new UsernameTakenException("Username " + contentDto.getUsername() 
                        + " is taken").getResponse();
        return super.createOne(content);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin"})
    public Response deleteOne(String content) {
        return super.deleteOne(content);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin", "User"})
    public Response updateOne(@Context HttpServletRequest request, String content) {
        try {
            UserDto dto = (UserDto) this.jsonToDto(content);
            int userId = dto.getId();
            JwtManager jwtm = new JwtManager();
            if (!jwtm.checkIfAdminOrMe(userId, request))
                throw new AuthorizationException("Non-Admin users are not authorized to " +
                        "edit other users profiles");
            UserDto userDto = (UserDto) repository.read(dto.getId());
            if (!dto.getUsername().equals(userDto.getUsername()) && 
                    ((UserRepository) repository).isUsernameTaken(dto.getUsername()))
                            return new UsernameTakenException("Username " + dto.getUsername() 
                            + " is taken").getResponse();
            return super.updateOne(content);
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
}
