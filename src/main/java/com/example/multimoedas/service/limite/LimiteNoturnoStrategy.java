package com.example.multimoedas.service.limite;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class LimiteNoturnoStrategy implements LimiteTransacaoStrategy {

    private static final BigDecimal LIMITE_NOTURNO = new BigDecimal("2000");

    @Override
    public BigDecimal valorMaximoPermitido() {
        return LIMITE_NOTURNO;
    }
}
