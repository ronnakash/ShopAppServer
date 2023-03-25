/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * @author ronna
 */
public class UsernameTakenException extends ShopApplicationException {

	public UsernameTakenException(String message) {
		this(message, null);
	}

	public UsernameTakenException(String message, Throwable e) {
		super(message, e, 400);
	}

}
