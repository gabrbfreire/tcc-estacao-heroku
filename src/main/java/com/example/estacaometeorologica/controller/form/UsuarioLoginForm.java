package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UsuarioLoginForm {

    @Email(message = "email inválido")
    @NotNull(message = "Insira o campo email")
    private String email;
    @NotNull(message = "Insira o campo senha")
    private String senha;

    public UsuarioLoginForm(){}

    public UsuarioLoginForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
