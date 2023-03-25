/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * @author ronna
 */
public class SubentityCreactionException extends ShopApplicationException {
	public SubentityCreactionException() {
		this((Throwable) null);
	}

	public SubentityCreactionException(Throwable e) {
		this("Failed to create subentity in create/update request", e, 500);
	}

	public SubentityCreactionException(String message) {
		this(message, null, 500);
	}

	public SubentityCreactionException(String message, Throwable e, int status) {
		super(message, e, status);
	}

	public SubentityCreactionException(String message, Throwable e) {
		this(message, e, 500);
	}
}
