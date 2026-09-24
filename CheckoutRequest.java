package com.fathimamart.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * POST /api/orders (checkout) payload.
 * Prices are never sent by the client - the server reads them from the products table.
 */
public record CheckoutRequest(
        @NotEmpty(message = "Your cart is empty")
        @Valid
        List<OrderItemRequest> items,

        @NotNull(message = "Shipping details are required")
        @Valid
        ShippingAddressRequest shipping,

        @NotNull(message = "Please choose a payment method")
        String paymentMethod
) {
}
