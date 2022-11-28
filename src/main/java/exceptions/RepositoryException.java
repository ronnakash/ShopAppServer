/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author ronna
 */
public class RepositoryException extends ShopApplicationException{
    
    public RepositoryException(){
        this((Throwable) null);
    }
    
    public RepositoryException(Throwable e){
        this("Repository transaction failed", e, 500);
    }
    
    public RepositoryException(String message){
        this(message, (Throwable) null, 500);
    }
        
    public RepositoryException(String message, Throwable e, int status){
        super(message, e, status);
    }
   
    public RepositoryException(String message, Throwable e){
        this(message, e, 500);
    }
    
}
