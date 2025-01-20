package com.fiap.spring_security_jwt.security;

import com.fiap.spring_security_jwt.service.TokenService;
import com.fiap.spring_security_jwt.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final UserService userService;
    private final TokenService tokenService;

    public SecurityFilter(@Lazy UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        final var token = getToken(request);

        if (token.isPresent()) {
            final var login = tokenService.validateToken(token.get());
            final var user = userService.loadUserByUsername(login);
            final var auth = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(final HttpServletRequest request) {
        final var auth = request.getHeader(AUTHORIZATION);
        return nonNull(auth)
                ? Optional.of(auth.replace(BEARER, ""))
                : Optional.empty();
    }
}
