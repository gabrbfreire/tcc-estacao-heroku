package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ResetarSenhaForm {

    @Email(message = "email inválido")
    @NotNull(message = "Insira o campo email")
    private String email;

    public ResetarSenhaForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
