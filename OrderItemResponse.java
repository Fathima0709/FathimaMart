package com.fathimamart.dto.response;

import com.fathimamart.entity.OrderItem;
import java.math.BigDecimal;

/**
 * One line of a placed order (includes the image of the product).
 */
public record OrderItemResponse(
        Long productId,
        String productName,
        String imageUrl,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {

    public static OrderItemResponse from(OrderItem item) {
        String image = item.getProduct() != null ? item.getProduct().getImageUrl() : null;
        Long productId = item.getProduct() != null ? item.getProduct().getId() : null;
        return new OrderItemResponse(productId, item.getProductName(), image,
                item.getQuantity(), item.getUnitPrice(), item.getSubtotal());
    }
}
