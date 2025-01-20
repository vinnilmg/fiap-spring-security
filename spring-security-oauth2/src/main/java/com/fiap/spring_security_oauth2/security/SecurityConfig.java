package com.fiap.spring_security_oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/ativos/buscar-todos").permitAll();
                            auth.requestMatchers("/logout").permitAll();
                            auth.anyRequest().authenticated();
                        }
                ).oauth2Login(Customizer.withDefaults())
                .build();
    }
}
