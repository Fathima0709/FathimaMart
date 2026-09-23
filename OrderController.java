package com.fathimamart.controller;

import com.fathimamart.dto.request.CheckoutRequest;
import com.fathimamart.dto.response.ApiResponse;
import com.fathimamart.dto.response.OrderResponse;
import com.fathimamart.exception.UnauthorizedException;
import com.fathimamart.security.CurrentUser;
import com.fathimamart.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Protected order endpoints (JWT required).
 *
 * POST /api/orders        place an order (checkout)
 * GET  /api/orders        my order history
 * GET  /api/orders/{id}   one of my orders
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(
            @AuthenticationPrincipal CurrentUser currentUser,
            @Valid @RequestBody CheckoutRequest request) {
        OrderResponse order = orderService.placeOrder(currentUser(currentUser).getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Order placed successfully", order));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> myOrders(
            @AuthenticationPrincipal CurrentUser currentUser) {
        List<OrderResponse> orders = orderService.findMyOrders(currentUser(currentUser).getId());
        return ResponseEntity.ok(ApiResponse.ok(orders.size() + " order(s)", orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> order(
            @AuthenticationPrincipal CurrentUser currentUser,
            @PathVariable Long id) {
        OrderResponse order = orderService.findOrder(currentUser(currentUser).getId(), id);
        return ResponseEntity.ok(ApiResponse.ok("Order found", order));
    }

    private CurrentUser currentUser(CurrentUser currentUser) {
        if (currentUser == null) {
            throw new UnauthorizedException("Authentication required. Please log in.");
        }
        return currentUser;
    }
}
