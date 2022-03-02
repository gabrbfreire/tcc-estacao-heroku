package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioSigninForm {

    @NotNull(message = "Insira o campo nome")
    @Pattern(regexp = "/^(([A-Z]|[ÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ])([a-z]|[áàâãéèêíïóôõöúçñ])+ ?){2,}$/g", message = "nome inválido")
    private String nome;
    @Email(message = "email inválido")
    @NotNull(message = "Insira o campo email")
    private String email;
    @NotNull(message = "Insira o campo senha")
    private String senha;
    @Pattern(regexp = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$", message = "imagem inválida")
    @Size(max = 60000, message = "Imagem tem que ter no máximo 150x150")
    private String imagem;

    public UsuarioSigninForm(){}

    public UsuarioSigninForm(String email) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
