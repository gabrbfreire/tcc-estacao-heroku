package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.NotNull;

public class AlterarSenhaForm {

    @NotNull(message = "Insira o campo nova_senha")
    private String nova_senha;

    public String getNova_senha() {
        return nova_senha;
    }

    public void setNova_senha(String nova_senha) {
        this.nova_senha = nova_senha;
    }
}
