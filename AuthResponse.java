package com.fathimamart.dto.response;

/**
 * Returned by login/register together with the JWT.
 */
public record AuthResponse(String token, UserResponse user, String message) {
}
