package com.fathimamart.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Delivery address sent with checkout.
 */
public record ShippingAddressRequest(
        @NotBlank(message = "Full name is required")
        @Size(max = 100, message = "Full name must be at most 100 characters")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Please enter a valid email address")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[0-9+\\-\\s]{7,20}$", message = "Please enter a valid phone number")
        String phone,

        @NotBlank(message = "Address is required")
        @Size(max = 255, message = "Address must be at most 255 characters")
        String address,

        @NotBlank(message = "City is required")
        @Size(max = 50, message = "City must be at most 50 characters")
        String city,

        @NotBlank(message = "State is required")
        @Size(max = 50, message = "State must be at most 50 characters")
        String state,

        @NotBlank(message = "Postal code is required")
        @Pattern(regexp = "^[0-9A-Za-z\\-\\s]{4,10}$", message = "Please enter a valid postal code")
        String postalCode
) {
}
