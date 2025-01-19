package com.fiap.spring_security_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ativo")
@Data
public class Ativo {

    @Id
    @GeneratedValue
    private UUID id;
    private String papel;
    private BigDecimal cotacao;
    private BigDecimal pl;
}
