package com.example.multimoedas.service.validation;

import org.springframework.stereotype.Component;

import com.example.multimoedas.model.Cartao;
import com.example.multimoedas.model.ValorEmMoeda;
import com.example.multimoedas.service.limite.SeletorLimiteTransacao;

@Component
public class ValidadorSaque {

    private final RegrasBasicasValidacao regras;
    private final SeletorLimiteTransacao seletorLimite;

    public ValidadorSaque(RegrasBasicasValidacao regras, SeletorLimiteTransacao seletorLimite) {
        this.regras = regras;
        this.seletorLimite = seletorLimite;
    }

    public void validarSaque(Cartao cartao, ValorEmMoeda entrada) {
        regras.validarSaque(cartao, entrada);
        regras.validarLimite(entrada.getValor(), seletorLimite.limiteVigente());
    }
}
