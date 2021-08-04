package com.example.estacaometeorologica.controller.form;

import com.example.estacaometeorologica.model.DadosColetados;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

public class DadosColetadosForm {

    private Double precipitacao;
    private Double velocidade_vento;
    private Double direcao_vento;
    private Double temperatura;
    private Double umidade_ar;
    private Double pressao_atmosferica;

    public DadosColetados converter() {
        return new DadosColetados(
               Instant.now().minusSeconds(10800),
               precipitacao,
               velocidade_vento,
               direcao_vento,
               temperatura,
               umidade_ar,
               pressao_atmosferica
       );
    }

    public Double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(Double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public Double getVelocidade_vento() {
        return velocidade_vento;
    }

    public void setVelocidade_vento(Double velocidade_vento) {
        this.velocidade_vento = velocidade_vento;
    }

    public Double getDirecao_vento() {
        return direcao_vento;
    }

    public void setDirecao_vento(Double direcao_vento) {
        this.direcao_vento = direcao_vento;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidade_ar() {
        return umidade_ar;
    }

    public void setUmidade_ar(Double umidade_ar) {
        this.umidade_ar = umidade_ar;
    }

    public Double getPressao_atmosferica() {
        return pressao_atmosferica;
    }

    public void setPressao_atmosferica(Double pressao_atmosferica) {
        this.pressao_atmosferica = pressao_atmosferica;
    }
}
