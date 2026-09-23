package com.fathimamart.controller;

import com.fathimamart.dto.response.ApiResponse;
import com.fathimamart.dto.response.ProductResponse;
import com.fathimamart.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public catalog endpoints.
 *
 * GET /api/products            ?search=&category=&sort=&minPrice=&maxPrice=
 * GET /api/products/featured
 * GET /api/products/categories
 * GET /api/products/{id}
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<ProductResponse> products = productService.search(search, category, sort, minPrice, maxPrice);
        return ResponseEntity.ok(ApiResponse.ok(products.size() + " product(s) found", products));
    }

    @GetMapping("/featured")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> featured() {
        return ResponseEntity.ok(ApiResponse.ok("Featured products", productService.featured()));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<String>>> categories() {
        return ResponseEntity.ok(ApiResponse.ok("Categories", productService.categories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> byId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Product found", productService.byId(id)));
    }
}
