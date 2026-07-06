package com.example.multimoedas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CriarCartaoRequest {
    
    @NotBlank(message = "Nome do titular é obrigatório")
    private String nomeTitular;
    
    @NotBlank(message = "CPF do titular é obrigatório")
    private String cpfTitular;
    
    @NotBlank(message = "Senha é obrigatória")
    @Pattern(regexp = "\\d{6}", message = "Senha deve conter exatamente 6 dígitos")
    private String senha;
    
    public CriarCartaoRequest() {}

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
}
