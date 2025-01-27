package com.fiap.spring_security_demo.security;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig
                                    .requestMatchers("ativos/buscar-todos")
                                    .permitAll();
                            authorizeConfig
                                    .requestMatchers("/logout")
                                    .permitAll();
                            authorizeConfig
                                    .anyRequest()
                                    .authenticated();
                        }
                ).httpBasic(Customizer.withDefaults())
                .build();
    }
}
