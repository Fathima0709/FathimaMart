package com.fathimamart.dto.response;

import com.fathimamart.entity.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Full order view returned to the customer who owns it.
 */
public record OrderResponse(
        Long id,
        String orderNumber,
        String status,
        LocalDateTime orderDate,
        BigDecimal totalAmount,
        String paymentMethod,
        String shippingFullName,
        String shippingEmail,
        String shippingPhone,
        String shippingAddress,
        String shippingCity,
        String shippingState,
        String shippingPostalCode,
        List<OrderItemResponse> items
) {

    public static OrderResponse from(Order order) {
        List<OrderItemResponse> items = order.getItems().stream().map(OrderItemResponse::from).toList();
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus() == null ? null : order.getStatus().name(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getShippingFullName(),
                order.getShippingEmail(),
                order.getShippingPhone(),
                order.getShippingAddress(),
                order.getShippingCity(),
                order.getShippingState(),
                order.getShippingPostalCode(),
                items
        );
    }
}
