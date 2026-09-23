package com.fathimamart.controller;

import com.fathimamart.dto.request.LoginRequest;
import com.fathimamart.dto.request.RegisterRequest;
import com.fathimamart.dto.response.ApiResponse;
import com.fathimamart.dto.response.AuthResponse;
import com.fathimamart.dto.response.UserResponse;
import com.fathimamart.exception.UnauthorizedException;
import com.fathimamart.security.CurrentUser;
import com.fathimamart.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public authentication endpoints + the protected "who am I" endpoint.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** POST /api/auth/register - create an account and receive a JWT. */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response.message(), response));
    }

    /** POST /api/auth/login - exchange email + password for a JWT. */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.ok(response.message(), response));
    }

    /** GET /api/auth/me - profile of the user who owns the bearer token. */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            throw new UnauthorizedException("Authentication required. Please log in.");
        }
        return ResponseEntity.ok(ApiResponse.ok("OK", authService.currentUser(currentUser.getUsername())));
    }
}
