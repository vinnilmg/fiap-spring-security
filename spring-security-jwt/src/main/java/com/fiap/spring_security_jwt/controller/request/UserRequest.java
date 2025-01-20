package com.fiap.spring_security_jwt.controller.request;

public record UserRequest(
        String login,
        String password,
        String role
) {
}
