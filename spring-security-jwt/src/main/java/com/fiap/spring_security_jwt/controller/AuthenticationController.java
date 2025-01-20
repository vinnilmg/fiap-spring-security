package com.fiap.spring_security_jwt.controller;

import com.fiap.spring_security_jwt.controller.request.UserAuthRequest;
import com.fiap.spring_security_jwt.controller.request.UserRequest;
import com.fiap.spring_security_jwt.controller.response.UserResponse;
import com.fiap.spring_security_jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody final UserAuthRequest request) {
        final var token = userService.login(request);
        return ResponseEntity.ok(UserResponse.of(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody final UserRequest request) {
        try {
            userService.saveUser(request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
