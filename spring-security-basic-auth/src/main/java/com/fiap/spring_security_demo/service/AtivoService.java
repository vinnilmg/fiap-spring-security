package com.fiap.spring_security_demo.service;

import com.fiap.spring_security_demo.entity.Ativo;
import com.fiap.spring_security_demo.repository.AtivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtivoService {
    private final AtivoRepository repository;

    public AtivoService(AtivoRepository repository) {
        this.repository = repository;
    }

    public List<Ativo> findAll() {
        return repository.findAll();
    }

    public List<Ativo> getAtivosOrderedByPlDesc() {
        return repository.findAllByOrderByPlDesc();
    }
}
