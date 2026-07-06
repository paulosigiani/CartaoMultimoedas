package com.example.multimoedas.service.interfaces;

import java.math.BigDecimal;

public interface ProvedorCotacao {

    BigDecimal obterTaxa(String moedaOrigem, String moedaDestino);
}
