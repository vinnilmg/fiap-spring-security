package com.fiap.spring_security_jwt.entity.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum UserRoleEnum {
    ADMIN("admin", "ROLE_ADMIN"),
    USER("user", "ROLE_USER");

    private final String role;
    private final String fullRole;

    UserRoleEnum(String role, String fullRole) {
        this.role = role;
        this.fullRole = fullRole;
    }

    public String getRole() {
        return role;
    }

    public String getFullRole() {
        return fullRole;
    }

    public static Optional<UserRoleEnum> toEnum(final String value) {
        return Stream.of(values())
                .filter(userRoleEnum -> userRoleEnum.getRole().equalsIgnoreCase(value))
                .findFirst();
    }
}
