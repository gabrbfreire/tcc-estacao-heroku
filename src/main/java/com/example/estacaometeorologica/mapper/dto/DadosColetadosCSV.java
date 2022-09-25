package com.example.estacaometeorologica.mapper.dto;

import com.example.estacaometeorologica.model.DadosColetados;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DadosColetadosCSV {
    @CsvBindByName(column = "Data")
    @CsvBindByPosition(position = 0)
    private String data;

    @CsvBindByName(column = "Direção do Vento (°)")
    @CsvBindByPosition(position = 1)
    private Double direcaoVento;

    @CsvBindByName(column = "Velocidade do Vento (Km/h)")
    @CsvBindByPosition(position = 2)
    private Double velocidadeVento;

    @CsvBindByName(column = "Precipitação (mm)")
    @CsvBindByPosition(position = 3)
    private Double precipitacao;

    @CsvBindByName(column = "Temperatura (°C)")
    @CsvBindByPosition(position = 4)
    private Double temperatura;

    @CsvBindByName(column = "Umidade Relativa do Ar (%)")
    @CsvBindByPosition(position = 5)
    private Double umidade;

    @CsvBindByName(column = "Pressão Atmosférica (hPa)")
    @CsvBindByPosition(position = 6)
    private Double pressaoAtmosferica;

    @CsvBindByName(column = "Nivel Bateria (%)")
    @CsvBindByPosition(position = 7)
    private Double nivelBateria;

    @CsvBindByName(column = "Cartao SD")
    @CsvBindByPosition(position = 8)
    private Boolean cartaoSd;

    public DadosColetadosCSV(DadosColetados dadosColetados) {
        data = LocalDateTime.ofInstant(dadosColetados.getData(), ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        precipitacao = dadosColetados.getPrecipitacao();
        pressaoAtmosferica = dadosColetados.getPressao_atmosferica();
        temperatura = dadosColetados.getTemperatura();
        umidade = dadosColetados.getUmidade_ar();
        direcaoVento = dadosColetados.getDirecao_vento();
        velocidadeVento = dadosColetados.getVelocidade_vento();
        nivelBateria = dadosColetados.getNivel_bateria();
        cartaoSd = dadosColetados.getCartao_sd();
    }
}