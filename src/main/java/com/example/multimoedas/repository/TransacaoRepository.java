package com.example.multimoedas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.Transacao;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByCartaoOrderByDataTransacaoDesc(Cartao cartao);
}
