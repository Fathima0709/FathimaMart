package com.fathimamart.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * POST /api/auth/register payload.
 */
public record RegisterRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must be at most 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must be at most 50 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Please enter a valid email address")
        @Size(max = 100, message = "Email must be at most 100 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters")
        String password,

        @Pattern(regexp = "^$|^[0-9+\\-\\s]{7,20}$", message = "Please enter a valid phone number")
        String phone
) {
}
