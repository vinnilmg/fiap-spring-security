package com.fiap.spring_security_oauth2.controller;

import com.fiap.spring_security_oauth2.entity.Ativo;
import com.fiap.spring_security_oauth2.service.AtivoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ativos")
public class AtivoController {
    private final AtivoService service;

    public AtivoController(AtivoService service) {
        this.service = service;
    }

    @GetMapping("/buscar-todos")
    public List<Ativo> getAll() {
        return service.findAll();
    }

    @GetMapping("/ordenar-por-pl")
    public List<Ativo> getAtivosOrderedByPlDesc() {
        return service.getAtivosOrderedByPlDesc();
    }
}
