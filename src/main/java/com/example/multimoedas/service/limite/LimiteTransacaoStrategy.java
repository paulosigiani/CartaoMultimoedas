package com.example.multimoedas.service.limite;

import java.math.BigDecimal;

public interface LimiteTransacaoStrategy {

    BigDecimal valorMaximoPermitido();
}
