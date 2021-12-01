package com.example.estacaometeorologica.mapper;

import com.example.estacaometeorologica.controller.dto.TTNUplinkDto;
import com.example.estacaometeorologica.model.DadosColetados;

import java.time.Instant;

public class DadosColetadosMapper {
    private DadosColetadosMapper() {
        //
    }

    public static DadosColetados mapToEntity(TTNUplinkDto dto) {
        if (dto == null || dto.getMessage() == null || dto.getMessage().getDadosColetados() == null) {
            throw new IllegalArgumentException("Formato de dados coletados inválido.");
        }

        final TTNUplinkDto.InternalDadosColetadosDto dadosDoTTN = dto.getMessage().getDadosColetados();
        return new DadosColetados(
                Instant.now().minusSeconds(10800),
                dadosDoTTN.getPrecipitacao(),
                dadosDoTTN.getVelocidadeVento(),
                dadosDoTTN.getDirecaoVento(),
                dadosDoTTN.getTemperatura(),
                dadosDoTTN.getUmidadeAr(),
                dadosDoTTN.getPressaoAtmosferica()
        );
    }
}
