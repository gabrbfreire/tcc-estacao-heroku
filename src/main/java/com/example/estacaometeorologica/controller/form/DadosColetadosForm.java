package com.example.estacaometeorologica.controller.form;

import com.example.estacaometeorologica.model.DadosColetados;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DadosColetadosForm {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime data;
    @NotNull @NotEmpty(message = "Insira precipitação")
    private String precipitacao;
    @NotNull @NotEmpty(message = "Insira velocidade_vento")
    private String velocidade_vento;
    @NotNull @NotEmpty(message = "Insira direcao_vento")
    private String direcao_vento;
    @NotNull @NotEmpty(message = "Insira temperatura")
    private String temperatura;
    @NotNull @NotEmpty(message = "Insira umidade_ar")
    private String umidade_ar;
    @NotNull @NotEmpty(message = "Insira pressao_atmosferica")
    private String pressao_atmosferica;

    public DadosColetados converter() {
        return new DadosColetados(
               data,
               precipitacao,
               velocidade_vento,
               direcao_vento,
               temperatura,
               umidade_ar,
               pressao_atmosferica
       );
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(String precipitacao) {
        this.precipitacao = precipitacao;
    }

    public String getVelocidade_vento() {
        return velocidade_vento;
    }

    public void setVelocidade_vento(String velocidade_vento) {
        this.velocidade_vento = velocidade_vento;
    }

    public String getDirecao_vento() {
        return direcao_vento;
    }

    public void setDirecao_vento(String direcao_vento) {
        this.direcao_vento = direcao_vento;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getUmidade_ar() {
        return umidade_ar;
    }

    public void setUmidade_ar(String umidade_ar) {
        this.umidade_ar = umidade_ar;
    }

    public String getPressao_atmosferica() {
        return pressao_atmosferica;
    }

    public void setPressao_atmosferica(String pressao_atmosferica) {
        this.pressao_atmosferica = pressao_atmosferica;
    }
}
