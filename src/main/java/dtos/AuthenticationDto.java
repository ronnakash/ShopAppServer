/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * @author ronna
 */
public class AuthenticationDto implements Dto {
	private final String message;
	private final UserDto user;
	private String token;

	public AuthenticationDto(UserDto user, String token) {
		this.user = user;
		this.token = token;
		this.message = "Logged in user " + user.getUsername() + " successfully";
	}

	public AuthenticationDto(UserDto user) {
		this.user = user;
		this.message = "Registered user " + user.getUsername() + " successfully";
	}

	public String getMessage() {
		return message;
	}

	public UserDto getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

}
