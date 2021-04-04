package com.example.estacaometeorologica.model;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class DadosColetados {

    @Id
    private String id;
    private LocalDateTime data;
    private Double precipitacao;
    private Double velocidade_vento;
    private Double direcao_vento;
    private Double temperatura;
    private Double umidade_ar;
    private Double pressao_atmosferica;

    public DadosColetados(LocalDateTime data, Double precipitacao, Double velocidade_vento, Double direcao_vento, Double temperatura, Double umidade_ar, Double pressao_atmosferica) {
        this.data = data;
        this.precipitacao = precipitacao;
        this.velocidade_vento = velocidade_vento;
        this.direcao_vento = direcao_vento;
        this.temperatura = temperatura;
        this.umidade_ar = umidade_ar;
        this.pressao_atmosferica = pressao_atmosferica;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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

