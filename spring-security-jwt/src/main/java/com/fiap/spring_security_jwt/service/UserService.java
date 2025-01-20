package com.fiap.spring_security_jwt.service;

import com.fiap.spring_security_jwt.controller.request.UserAuthRequest;
import com.fiap.spring_security_jwt.controller.request.UserRequest;
import com.fiap.spring_security_jwt.entity.User;
import com.fiap.spring_security_jwt.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserService(
            UserRepository repository,
            @Lazy AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void saveUser(final UserRequest request) {
        final var user = loadUserByUsername(request.login());
        if (nonNull(user)) throw new IllegalArgumentException("User already exists");

        final var encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        final var newUser = new User(request.login(), encryptedPassword, request.role());
        repository.save(newUser);
    }

    public String login(final UserAuthRequest request) {
        final var credentials = new UsernamePasswordAuthenticationToken(
                request.login(),
                request.password()
        );

        final var auth = authenticationManager.authenticate(credentials);
        return tokenService.generateToken((User) auth.getPrincipal());
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return repository.findByLogin(username);
    }
}
