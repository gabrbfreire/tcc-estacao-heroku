package com.example.estacaometeorologica.controller.dto;

public class TokenDto {

    private String token;
    private String tipo_autenticacao;

    public TokenDto (String token, String tipoAuth) {
        this.token = token;
        this.tipo_autenticacao = tipoAuth;
    }

    public String getToken() {
        return token;
    }

    public String getTipo_autenticacao() {
        return tipo_autenticacao;
    }
}
