/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.openuproject.shopappserver.resources;

import annotations.Logged;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ronna
 */
@javax.ws.rs.ApplicationPath("/api")
@Logged
public class ShopApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.openuproject.shopappserver.resources.AuthenticationResource.class);
        resources.add(com.openuproject.shopappserver.resources.CategoryResource.class);
        resources.add(com.openuproject.shopappserver.resources.ItemResource.class);
        resources.add(com.openuproject.shopappserver.resources.OrderResource.class);
        resources.add(com.openuproject.shopappserver.resources.UserResource.class);
        resources.add(filters.JwtFilter.class);
        resources.add(filters.LogFilter.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
    }
    
}
