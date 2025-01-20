package com.fiap.spring_security_jwt.entity;

import com.fiap.spring_security_jwt.entity.enums.UserRoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.fiap.spring_security_jwt.entity.enums.UserRoleEnum.ADMIN;
import static com.fiap.spring_security_jwt.entity.enums.UserRoleEnum.USER;

@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public User() {
    }

    public User(final String login, final String password, final String role) {
        this.login = login;
        this.password = password;
        this.role = UserRoleEnum.toEnum(role)
                .orElseThrow(() -> new IllegalArgumentException("User Role is invalid"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.equals(ADMIN)
                ? List.of(new SimpleGrantedAuthority(ADMIN.getRole()), new SimpleGrantedAuthority(USER.getRole()))
                : List.of(new SimpleGrantedAuthority(USER.getRole()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
