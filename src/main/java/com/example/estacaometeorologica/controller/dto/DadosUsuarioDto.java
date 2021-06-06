package com.example.estacaometeorologica.controller.dto;

public class DadosUsuarioDto {

    private String nome;
    private String token;
    private String tipo_autenticacao;

    public DadosUsuarioDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo_autenticacao() {
        return tipo_autenticacao;
    }

    public void setTipo_autenticacao(String tipo_autenticacao) {
        this.tipo_autenticacao = tipo_autenticacao;
    }
}
