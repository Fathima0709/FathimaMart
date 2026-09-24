package com.fathimamart.dto.response;

import com.fathimamart.entity.User;
import java.time.LocalDateTime;

/**
 * Public view of a user account (password is never exposed).
 */
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String fullName,
        String email,
        String phone,
        String role,
        LocalDateTime createdAt
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole() == null ? null : user.getRole().name(),
                user.getCreatedAt()
        );
    }
}
