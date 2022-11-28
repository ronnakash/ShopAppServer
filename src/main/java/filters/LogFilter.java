/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import annotations.Logged;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ronna
 */



@Provider
@Logged
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {
        System.out.println("-- req info --");
        logReq(reqContext.getUriInfo(), reqContext.getHeaders());

    }

    @Override
    public void filter(ContainerRequestContext reqContext,
                       ContainerResponseContext resContext) throws IOException {
        System.out.println("-- res info --");
        logRes(reqContext.getUriInfo(), resContext.getHeaders(), resContext.getStatus());


    }

    private void logReq(UriInfo uriInfo, MultivaluedMap<String, ?> headers) {
        System.out.println("Path: " + uriInfo.getPath());
        headers.entrySet().forEach(h -> System.out.println(h.getKey() + ": " + h.getValue()));
    }
    
    private void logRes(UriInfo uriInfo, MultivaluedMap<String, ?> headers, int status) {
        System.out.println("Status: " + status);
        System.out.println("Path: " + uriInfo.getPath());
        headers.entrySet().forEach(h -> System.out.println(h.getKey() + ": " + h.getValue()));

    }

}

