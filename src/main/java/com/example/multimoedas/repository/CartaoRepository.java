package com.example.multimoedas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multimoedas.model.Cartao;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNumeroCartao(String numeroCartao);
}
