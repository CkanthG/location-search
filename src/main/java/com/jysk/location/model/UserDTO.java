package com.jysk.location.model;

/**
 * This record is used to bind user details.
 * @param userId is used to read from UI to log inside the application.
 * @param password is used to read from UI.
 */
public record UserDTO(String userId, String password) {
}
