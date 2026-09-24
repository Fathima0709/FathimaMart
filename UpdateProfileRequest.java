package com.fathimamart.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * PUT /api/users/me payload - update profile basics.
 */
public record UpdateProfileRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must be at most 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must be at most 50 characters")
        String lastName,

        @jakarta.validation.constraints.Pattern(regexp = "^$|^[0-9+\\-\\s]{7,20}$", message = "Please enter a valid phone number")
        String phone
) {
}
