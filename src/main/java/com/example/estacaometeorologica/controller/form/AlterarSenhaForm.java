package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.NotNull;

public class AlterarSenhaForm {

    @NotNull(message = "Insira o campo atual_senha")
    private String atual_senha;

    @NotNull(message = "Insira o campo nova_senha")
    private String nova_senha;

    public String getNova_senha() {
        return nova_senha;
    }

    public void setNova_senha(String nova_senha) {
        this.nova_senha = nova_senha;
    }

    public String getAtual_senha() {
        return atual_senha;
    }

    public void setAtual_senha(String atual_senha) {
        this.atual_senha = atual_senha;
    }
}
