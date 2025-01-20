package com.fiap.spring_security_oauth2.repository;

import com.fiap.spring_security_oauth2.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtivoRepository extends JpaRepository<Ativo, String> {
    List<Ativo> findAllByOrderByPlDesc();
}
