package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.Size;

public class ImagemUsuarioForm {

    @Size(max = 60000, message = "Imagem tem que ter no máximo 150x150")
    private String imagem;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
