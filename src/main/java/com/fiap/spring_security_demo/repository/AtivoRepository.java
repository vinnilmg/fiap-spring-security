package com.fiap.spring_security_demo.repository;

import com.fiap.spring_security_demo.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtivoRepository extends JpaRepository<Ativo, String> {
    List<Ativo> findAllByOrderByPlDesc();
}
