package com.fathimamart.dto.response;

import com.fathimamart.entity.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Public product view used by every product listing/detail endpoint.
 */
public record ProductResponse(
        Long id,
        String name,
        String brand,
        String category,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        Boolean featured,
        BigDecimal rating,
        Integer reviewCount,
        boolean inStock,
        LocalDateTime createdAt
) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                product.getFeatured(),
                product.getRating(),
                product.getReviewCount(),
                product.isInStock(),
                product.getCreatedAt()
        );
    }
}
