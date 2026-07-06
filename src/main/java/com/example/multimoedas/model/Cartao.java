package com.example.multimoedas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "cartoes")
public class Cartao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_cartao", unique = true, nullable = false)
    private String numeroCartao;
    
    @Column(name = "nome_titular", nullable = false)
    private String nomeTitular;

    @Column(name = "cpf_titular", nullable = false, length = 11)
    private String cpfTitular;

    @Column(name = "senha", nullable = false, length = 6)
    private String senha;

    @ElementCollection
    @CollectionTable(name = "saldos_cartao", joinColumns = @JoinColumn(name = "cartao_id"))
    @MapKeyColumn(name = "moeda")
    @Column(name = "saldo", precision = 17, scale = 2)
    private Map<String, BigDecimal> saldos = new HashMap<>();
    
    @Column(name = "data_emissao")
    private LocalDateTime dataEmissao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCartao status;

    public BigDecimal saldoDe(String moeda) {
        return saldos.getOrDefault(moeda, BigDecimal.ZERO);
    }

    public void creditar(String moeda, BigDecimal valor) {
        saldos.put(moeda, saldoDe(moeda).add(valor));
    }

    public void debitar(String moeda, BigDecimal valor) {
        saldos.put(moeda, saldoDe(moeda).subtract(valor));
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumeroCartao() {
        return numeroCartao;
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    public String getNomeTitular() {
        return nomeTitular;
    }
    
    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Map<String, BigDecimal> getSaldos() {
        return saldos;
    }
    
    public void setSaldos(Map<String, BigDecimal> saldos) {
        this.saldos = saldos;
    }
    
    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }
    
    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    
    public StatusCartao getStatus() {
        return status;
    }
    
    public void setStatus(StatusCartao status) {
        this.status = status;
    }
}
