package com.fathimamart.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Error payload: { "success": false, "message": "...", "errors": { field: msg } }
 */
public record ErrorResponse(boolean success, String message, Map<String, String> errors, LocalDateTime timestamp) {

    public static ErrorResponse of(String message) {
        return new ErrorResponse(false, message, null, LocalDateTime.now());
    }

    public static ErrorResponse of(String message, Map<String, String> errors) {
        return new ErrorResponse(false, message, errors, LocalDateTime.now());
    }
}
