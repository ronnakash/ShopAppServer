/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.repositories;

import com.openuproject.shopappserver.dtos.EntityDto;
import entities.DatabaseEntity;
import exceptions.RepositoryException;
import exceptions.ShopApplicationException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author ronna
 */
public abstract class RepositoryAbst<T extends DatabaseEntity> implements Repository<T> {
    final EntityManager entityManager = ShopEntityManagerFactory.getEntityManager();
    Class<T> type;
    TypedQuery<T> findByIdQuery;
    TypedQuery<T> findAllQuery;
    
    public RepositoryAbst(String name, Class<T> type) {
        this.type = type;
        this.findByIdQuery = entityManager.createNamedQuery(name + ".findById", this.type);
        this.findAllQuery = entityManager.createNamedQuery(name + ".findAll", this.type);
    }    

    @Override
    public List<EntityDto<T>> readAll() {
        try {
            List res = findAllQuery
                    .getResultList()
                    .stream()
                    .map(entity -> entity.toDto())
                    .collect(Collectors.toList());
            return res;
        }
        catch (ShopApplicationException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RepositoryException("Failed getting entities", e);
        }
    }

    @Override
    public EntityDto<T> create(EntityDto<T> tDto) {
        try {
            T entity = tDto.toEntity();
            EntityTransaction t = entityManager.getTransaction();
            t.begin();
            entityManager.persist(entity);
            t.commit();
            return entity.toDto();
        }
        catch (ShopApplicationException e) {
            throw e;
        }
        catch (Exception e) {
//            if (e instanceof ShopApplicationException)
//                throw e;
//            else 
                throw new RepositoryException("Failed creating entity", e);
        }    
    }

    @Override
    public EntityDto<T> read(int id) {
        try {
            return findByIdQuery
                    .setParameter("id", id)
                    .getSingleResult().toDto();
        }
        catch (ShopApplicationException e) {
            throw e;
        }
        catch (Exception e) {
//            if (e instanceof ShopApplicationException)
//                throw e;
//            else 
                throw new RepositoryException("Failed reading entity with id " + id, e);
        }    
    }

    @Override
    public EntityDto<T> update(EntityDto<T> dto) {
        try {
            T entity = this.readLazy(dto.getId());
            EntityTransaction t = entityManager.getTransaction();
            t.begin();
            entity.update(dto);
            entityManager.persist(entity);
            t.commit();
            return entity.toDto();
        }
        catch (ShopApplicationException e) {
            throw e;
        }
        catch (Exception e) {
//            if (e instanceof ShopApplicationException)
//                throw e;
//            else 
                throw new RepositoryException("Failed updating entity", e);
        }
    }

    @Override
    public EntityDto<T> delete(EntityDto<T> dto) {
        try {
            T entity = this.readLazy(dto.getId());
            EntityTransaction t = entityManager.getTransaction();
            t.begin();
            entityManager.remove(entity);
            t.commit();
            return entity.toDto();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                throw e;
            else 
                throw new RepositoryException("Failed deleting entity", e);
        }
    }
    
    @Override
    public T readLazy(int id) {
        return entityManager.getReference(type, id);
    }

    List<EntityDto> toDtos(List<T> entityList){
        return entityList
                .stream()
                .map(entity -> entity.toDto())
                .collect(Collectors.toList());
    }
    
    public T getEntityById(int id){
        try {
            return findByIdQuery
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (Exception e) {
            if (e instanceof ShopApplicationException)
                throw e;
            else 
                throw new RepositoryException("Failed getting entity with id", e);
        }
    }
    
}
