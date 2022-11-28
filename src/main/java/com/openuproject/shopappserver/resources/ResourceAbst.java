/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openuproject.shopappserver.dtos.EntityDto;
import com.openuproject.shopappserver.repositories.Repository;
import entities.DatabaseEntity;
import exceptions.ShopApplicationException;
import exceptions.UnhandledExceptionMapper;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ronna
 */
public abstract class ResourceAbst<T extends DatabaseEntity> {
    Repository repository;
    Gson gson;
    Type dtoType;
    Type dtoListType;
    
    @Context
    private UriInfo context;
    
    
    ResourceAbst(Repository repository, Type dtoType, Type dtoListType){
        gson = new GsonBuilder().create();
        this.repository = repository;
        this.dtoType = dtoType;
        this.dtoListType = dtoListType;
    }
    
    public Response getAll() {
        try {
            List dtos = repository.readAll();
            String json = this.dtosToJson(dtos);
            return Response.ok(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
    public Response getOne(int id) {
        try {
            EntityDto dto = repository.read(id);
            String json = this.dtoToJson(dto);
            return Response.ok(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
    public Response createOne(String content) {
        try {
            EntityDto contentDto = this.jsonToDto(content);
            EntityDto dto = repository.create(contentDto);
            String json = this.dtoToJson(dto);
            return Response.status(201).entity(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
    public Response deleteOne(String content) {
        try {
            EntityDto contentDto = this.jsonToDto(content);
            EntityDto dto = repository.delete(contentDto);
            String json = this.dtoToJson(dto);
            return Response.ok(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
    public Response updateOne(String content) {
        try {
            EntityDto contentDto = this.jsonToDto(content);
            EntityDto dto = repository.update(contentDto);
            String json = this.dtoToJson(dto);
            return Response.status(201).entity(json).build();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                return ((ShopApplicationException) e).getResponse();
            return new UnhandledExceptionMapper().toResponse(e);
        }
    }
    
    public String dtoToJson(EntityDto dto){
        return gson.toJson(dto, dtoType);
    }
    
    public String dtosToJson(List dtos){
        return gson.toJson(dtos, dtoListType);
    }
    
    public EntityDto<T> jsonToDto(String json){
        return (EntityDto<T>) gson.fromJson(json, dtoType);
    }
    
    public List<EntityDto<T>> jsonToDtoList(String json){
        return (List<EntityDto<T>>) gson.fromJson(json, dtoListType);
    }
    
}
