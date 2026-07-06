package com.example.multimoedas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.multimoedas.dto.CriarCartaoRequest;
import com.example.multimoedas.dto.CriarCartaoResponse;
import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.StatusCartao;
import com.example.multimoedas.repository.CartaoRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public CriarCartaoResponse criarCartao(CriarCartaoRequest request) {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(gerarNumeroCartao());
        cartao.setNomeTitular(request.getNomeTitular());
        cartao.setCpfTitular(request.getCpfTitular());
        cartao.setSenha(request.getSenha());
        cartao.setDataEmissao(LocalDateTime.now());
        cartao.setStatus(StatusCartao.ATIVO);
        Cartao salvo = cartaoRepository.save(cartao);
        return new CriarCartaoResponse(salvo.getNumeroCartao(), salvo.getDataEmissao(),
                salvo.getStatus().name());
    }

    public Cartao buscarCartao(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado: " + numeroCartao));
    }

    private String gerarNumeroCartao() {
        Random random = new Random();
        String prefixo = "666750";
        StringBuilder sufixo = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sufixo.append(random.nextInt(10));
        }
        return prefixo + sufixo;
    }
}
