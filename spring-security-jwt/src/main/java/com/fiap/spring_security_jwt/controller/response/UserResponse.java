package com.fiap.spring_security_jwt.controller.response;

public record UserResponse(String token) {
    public static UserResponse of(final String token) {
        return new UserResponse(token);
    }
}
