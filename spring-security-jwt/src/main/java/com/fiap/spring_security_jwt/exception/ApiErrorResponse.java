package com.fiap.spring_security_jwt.exception;

public record ApiErrorResponse(
        String type,
        String message
) {
    public static ApiErrorResponse of(final String type, final String message) {
        return new ApiErrorResponse(type, message);
    }
}
