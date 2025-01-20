package com.fiap.spring_security_jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fiap.spring_security_jwt.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private static final String ISSUER = "auth-api";

    @Value("${api.security.token.secret")
    private String secret;

    public String generateToken(final User user) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(generateExpirationDate())
                .sign(getAlgorithm());
    }

    public String validateToken(final String token) {
        return JWT.require(getAlgorithm())
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
