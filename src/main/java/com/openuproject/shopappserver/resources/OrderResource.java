/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import annotations.PermissionsRequired;
import com.google.gson.reflect.TypeToken;
import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.dtos.OrderDto;
import com.openuproject.shopappserver.dtos.UserDto;
import com.openuproject.shopappserver.repositories.OrderRepository;
import com.openuproject.shopappserver.repositories.UserRepository;
import entities.ShopOrder;
import entities.User;
import exceptions.AuthorizationException;
import exceptions.ShopApplicationException;
import exceptions.UnhandledExceptionMapper;
import exceptions.UsernameTakenException;
import io.jsonwebtoken.Claims;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.JwtManager;

/**
 * REST Web Service
 *
 * @author ronna
 */
@Path("/order")
public class OrderResource extends ResourceAbst<ShopOrder> {
    
    @Context
    private UriInfo context;
    
    @Context
    private ResourceInfo resourceInfo;


    public OrderResource() {
        super(new OrderRepository(), OrderDto.class, new TypeToken<List<OrderDto>>() {}.getType());
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"User", "Admin"})
    public Response getMyOrders(@Context HttpServletRequest request) {
        try {
            int userId = new JwtManager().getUserIdFromRequest(request);
            List dtos = ((OrderRepository) super.repository).findByUserId(userId);
            String json = this.dtosToJson(dtos);
            return Response.ok(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"User", "Admin"})
    public Response createOne(@Context HttpServletRequest request, String content) {
        try {
            OrderDto dto = (OrderDto) this.jsonToDto(content);
            int userId = dto.getUserId();
            JwtManager jwtm = new JwtManager();
            if (!jwtm.checkIfAdminOrMe(userId, request))
                throw new AuthorizationException("Non-Admin users are not authorized to " +
                        "create orders with user id that is not their id");
            if (dto.getOrderedItems().isEmpty())
                throw new ShopApplicationException("Can't create empty order!", 400) {};
            return super.createOne(content);
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin"})
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionsRequired(requiredPermissions = {"Admin", "User"})
    @Path("/status")
    public Response updateStatus(@Context HttpServletRequest request, String content) {
        try {
            OrderDto orderDto = (OrderDto) this.jsonToDto(content);
            int userId = orderDto.getUserId();
            JwtManager jwtm = new JwtManager();
            if (!jwtm.checkIfAdminOrMe(userId, request))
                throw new AuthorizationException("You are not authorized to change" + 
                        "this order's status");
            EntityDto dto = ((OrderRepository) repository).updateStatus(orderDto);
            String json = this.dtoToJson(dto);
            return Response.status(201).entity(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
}

