package com.example.estacaometeorologica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TTNUplinkDto {

    @JsonProperty("uplink_message")
    private UplinkMessageDto message;

    public UplinkMessageDto getMessage() { return message; }

    public static class UplinkMessageDto {
        @JsonProperty(value = "decoded_payload")
        private InternalDadosColetadosDto dadosColetados;

        public InternalDadosColetadosDto getDadosColetados() { return dadosColetados; }

    }
    public static class InternalDadosColetadosDto {
        private Double precipitacao;

        @JsonProperty(value = "velocidade_vento")
        private Double velocidadeVento;

        @JsonProperty(value = "direcao_vento")
        private Double direcaoVento;

        private Double temperatura;

        @JsonProperty(value = "umidade_ar")
        private Double umidadeAr;

        @JsonProperty(value = "pressao_atmosferica")
        private Double pressaoAtmosferica;

        @JsonProperty(value = "nivel_bateria")
        private Double nivelBateria;

        @JsonProperty(value = "cartao_sd")
        private Boolean cartaoSD;

        public Double getPrecipitacao() { return precipitacao; }

        public Double getVelocidadeVento() { return velocidadeVento; }

        public Double getDirecaoVento() { return direcaoVento; }

        public Double getTemperatura() { return temperatura; }

        public Double getUmidadeAr() { return umidadeAr; }

        public Double getPressaoAtmosferica() { return pressaoAtmosferica; }

        public Double getNivelBateria() { return nivelBateria; }

        public Boolean getCartaoSD() { return cartaoSD; }


    }
}
