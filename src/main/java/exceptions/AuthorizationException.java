/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author ronna
 */
public class AuthorizationException extends ShopApplicationException{
    public AuthorizationException(Throwable e){
        this("Error while authenticating user", e);
    }
    
    public AuthorizationException(String[] rp, String up){
        this("Action requires at least " + rp[0] + " level permissions while " 
                + "user only has " + up + " level permissions");
    }
    public AuthorizationException(String message){
        this(message, (Throwable) null);
    }
        
    public AuthorizationException(String message, Throwable e){
        super(message, e);
    }
    
    public AuthorizationException(){
        this((Throwable) null);
    }
    

    
}
