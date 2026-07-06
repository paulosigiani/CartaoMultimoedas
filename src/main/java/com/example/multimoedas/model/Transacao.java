package com.example.multimoedas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoTransacao tipo;
    
    @Column(name = "moeda", nullable = false)
    private String moeda;
    
    @Column(name = "valor_real_brasileiro", nullable = false, precision = 17, scale = 2)
    private BigDecimal valorRealBrasileiro;
    
    @Column(name = "taxa_conversao", precision = 17, scale = 6)
    private BigDecimal taxaConversao;
    
    @Column(name = "valor_convertido_moeda_destino", precision = 17, scale = 2)
    private BigDecimal valorConvertidoMoedaDestino;
    
    @Column(name = "data_transacao")
    private LocalDateTime dataTransacao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTransacao status;
    
    @Column(name = "descricao")
    private String descricao;
    
    public Transacao() {
        this.dataTransacao = LocalDateTime.now();
        this.status = StatusTransacao.PENDENTE;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Cartao getCartao() {
        return cartao;
    }
    
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    
    public TipoTransacao getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
    
    public String getMoeda() {
        return moeda;
    }
    
    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }
    
    public BigDecimal getValorRealBrasileiro() {
        return valorRealBrasileiro;
    }
    
    public void setValorRealBrasileiro(BigDecimal valorRealBrasileiro) {
        this.valorRealBrasileiro = valorRealBrasileiro;
    }
    
    public BigDecimal getTaxaConversao() {
        return taxaConversao;
    }
    
    public void setTaxaConversao(BigDecimal taxaConversao) {
        this.taxaConversao = taxaConversao;
    }
    
    public BigDecimal getValorConvertidoMoedaDestino() {
        return valorConvertidoMoedaDestino;
    }
    
    public void setValorConvertidoMoedaDestino(BigDecimal valorConvertidoMoedaDestino) {
        this.valorConvertidoMoedaDestino = valorConvertidoMoedaDestino;
    }
    
    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }
    
    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    
    public StatusTransacao getStatus() {
        return status;
    }
    
    public void setStatus(StatusTransacao status) {
        this.status = status;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
