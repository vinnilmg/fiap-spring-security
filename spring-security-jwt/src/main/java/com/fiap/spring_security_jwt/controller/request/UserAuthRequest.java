package com.fiap.spring_security_jwt.controller.request;

public record UserAuthRequest(
        String login,
        String password
) {
}
