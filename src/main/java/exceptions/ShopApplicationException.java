/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author ronna
 */
public abstract class ShopApplicationException extends WebApplicationException {

	public ShopApplicationException() {
		this((Throwable) null);
	}

	public ShopApplicationException(Throwable e) {
		this("Unexpected Error!", e, 500);
	}

	public ShopApplicationException(String message) {
		this(message, null);
	}

	public ShopApplicationException(String message, Throwable e, int status) {
		super(e, Response.status(status).entity(message).build());
	}

	public ShopApplicationException(String message, Throwable e) {
		this(message, e, 500);
	}

	public ShopApplicationException(String message, int status) {
		this(message, null, status);
	}


}
