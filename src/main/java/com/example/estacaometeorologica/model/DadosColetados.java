package com.example.estacaometeorologica.model;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class DadosColetados {

    @Id
    private String id;
    private LocalDateTime data;
    private String precipitacao;
    private String velocidade_vento;
    private String direcao_vento;
    private String temperatura;
    private String umidade_ar;
    private String pressao_atmosferica;

    public DadosColetados(LocalDateTime data, String precipitacao, String velocidade_vento, String direcao_vento, String temperatura, String umidade_ar, String pressao_atmosferica) {
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

