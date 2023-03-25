/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * @author ronna
 */
public class PasswordChiperException extends ShopApplicationException {

	public PasswordChiperException(Throwable e) {
		this("Error while encrypting/decrypting password", e);
	}

	public PasswordChiperException(String message, Throwable e) {
		super(message, e);
	}
}
