package com.fathimamart.controller;

import com.fathimamart.dto.response.ApiResponse;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Small health/protected-test endpoint.
 * GET /api/health        -> public
 * GET /api/test/protected-> requires a valid JWT
 */
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, String>>> health() {
        return ResponseEntity.ok(ApiResponse.ok("FATHIMA MART API is running",
                Map.of("service", "fathima-mart", "status", "UP")));
    }

    @GetMapping("/test/protected")
    public ResponseEntity<ApiResponse<String>> protectedTest(
            @org.springframework.security.core.annotation.AuthenticationPrincipal
            com.fathimamart.security.CurrentUser currentUser) {
        return ResponseEntity.ok(ApiResponse.ok(
                "You are authenticated",
                "Hello " + (currentUser == null ? "guest" : currentUser.getFullName())));
    }
}
