/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import annotations.Logged;
import annotations.PermissionsRequired;
import com.google.gson.reflect.TypeToken;
import com.openuproject.shopappserver.dtos.ItemDto;
import com.openuproject.shopappserver.repositories.ItemRepository;
import entities.Item;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ronna
 */
@Path("/item")
public class ItemResource extends ResourceAbst<Item> {
    
    @Context
    private UriInfo context;

    public ItemResource() {
        super(new ItemRepository(), ItemDto.class, new TypeToken<List<ItemDto>>() {}.getType());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return super.getAll();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id) {
        return super.getOne(id);
    }

        
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin"})
    public Response createOne(String content) {
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
    @PermissionsRequired(requiredPermissions = {"Admin"})
    public Response updateOne(String content) {
        return super.updateOne(content);
    }
    
}