package com.example.estacaometeorologica.mapper;

import com.example.estacaometeorologica.controller.dto.TTNUplinkDto;
import com.example.estacaometeorologica.mapper.dto.DadosColetadosCSV;
import com.example.estacaometeorologica.model.DadosColetados;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DadosColetadosMapper {
    private DadosColetadosMapper() {
        //
    }

    public static DadosColetados mapToEntity(TTNUplinkDto dto) {
        if (dto == null || dto.getMessage() == null || dto.getMessage().getDadosColetados() == null
                || dto.getMessage().getDadosColetados().getDirecaoVento() == null
                || dto.getMessage().getDadosColetados().getPrecipitacao() == null
                || dto.getMessage().getDadosColetados().getPressaoAtmosferica() == null
                || dto.getMessage().getDadosColetados().getTemperatura() == null
                || dto.getMessage().getDadosColetados().getUmidadeAr() == null
                || dto.getMessage().getDadosColetados().getVelocidadeVento() == null
                || dto.getMessage().getDadosColetados().getNivelBateria() == null) {
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
                dadosDoTTN.getPressaoAtmosferica(),
                dadosDoTTN.getNivelBateria(),
                dadosDoTTN.getCartaoSD()
        );
    }

    public static List<DadosColetadosCSV> mapToCSVList(List<DadosColetados> dadosColetados) {
        return dadosColetados.stream().map(DadosColetadosCSV::new).collect(Collectors.toList());
    }
}
