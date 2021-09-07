package com.example.estacaometeorologica.controller.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ImagemUsuarioForm {

    @Pattern(regexp = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$", message = "imagem inválida")
    @Size(max = 60000, message = "Imagem tem que ter no máximo 150x150")
    private String imagem;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
