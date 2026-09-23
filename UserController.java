package com.fathimamart.controller;

import com.fathimamart.dto.request.ChangePasswordRequest;
import com.fathimamart.dto.request.UpdateProfileRequest;
import com.fathimamart.dto.response.ApiResponse;
import com.fathimamart.dto.response.UserResponse;
import com.fathimamart.exception.UnauthorizedException;
import com.fathimamart.security.CurrentUser;
import com.fathimamart.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Protected "My Account" endpoints (JWT required).
 *
 * GET  /api/users/me            profile
 * PUT  /api/users/me            edit first name / last name / phone
 * PUT  /api/users/me/password   change password
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(ApiResponse.ok("OK", userService.getProfile(requireUser(currentUser).getId())));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> update(
            @AuthenticationPrincipal CurrentUser currentUser,
            @Valid @RequestBody UpdateProfileRequest request) {
        UserResponse updated = userService.updateProfile(requireUser(currentUser).getId(), request);
        return ResponseEntity.ok(ApiResponse.ok("Profile updated", updated));
    }

    @PutMapping("/me/password")
    public ResponseEntity<ApiResponse<UserResponse>> changePassword(
            @AuthenticationPrincipal CurrentUser currentUser,
            @Valid @RequestBody ChangePasswordRequest request) {
        UserResponse updated = userService.changePassword(requireUser(currentUser).getId(), request);
        return ResponseEntity.ok(ApiResponse.ok("Password changed successfully", updated));
    }

    private CurrentUser requireUser(CurrentUser currentUser) {
        if (currentUser == null) {
            throw new UnauthorizedException("Authentication required. Please log in.");
        }
        return currentUser;
    }
}
