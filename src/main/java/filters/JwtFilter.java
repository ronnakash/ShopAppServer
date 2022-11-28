/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import annotations.PermissionsRequired;
import exceptions.AuthorizationException;
import io.jsonwebtoken.Claims;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import util.JwtManager;

/**
 *
 * @author ronna
 */
@Provider
@PermissionsRequired
public class JwtFilter implements ContainerRequestFilter {
    JwtManager jwtm = new JwtManager();

    @Context
    private ResourceInfo resourceInfo;
    

    private PermissionsRequired getPRFromClass() {
        return resourceInfo.getResourceClass().getAnnotation(PermissionsRequired.class);
    }
    
    private PermissionsRequired getPRFromMethod() {
        return resourceInfo.getResourceMethod().getAnnotation(PermissionsRequired.class);
    }
    
    
    private PermissionsRequired getPR(){
        PermissionsRequired classPR, methodPR;
        try{
            classPR = getPRFromClass();
            methodPR = getPRFromMethod();
        }
        catch (Exception e) {
            throw new AuthorizationException("Error while getting PermissionsRequired annotation in JwtFilter", e);
        }
        if (methodPR == null && classPR == null) 
            throw new AuthorizationException("Cannot get PermissionsRequired annotation in JwtFilter");
        return methodPR==null? classPR : methodPR;
    }
    
    private boolean hasPermissions(String up, String[] rp) {
        for (String p : rp) {
            if (p.equals(up))
                return true;
        }
        return false;
    }
        
    @Override
    public void filter(ContainerRequestContext reqContext){
        String[] requiredPermissions = getPR().requiredPermissions();
        //if Jwt is not needed, return before decoding
        if(requiredPermissions.length==1 && requiredPermissions[0].equals("All"))
            return;
        Claims claims = jwtm.getDecodedJwtFromContext(reqContext);
        String userPermissions = claims.get("permissions", String.class);
        if (!hasPermissions(userPermissions, requiredPermissions))
            throw new AuthorizationException(requiredPermissions, userPermissions);
    }


}

