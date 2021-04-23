package com.example.estacaometeorologica.controller.dto;

public class DadosUsuarioDto {

    private String nome;
    private String imagem;
    private String token;
    private String tipo_autenticacao;

    public DadosUsuarioDto(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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
