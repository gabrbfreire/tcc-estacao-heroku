package com.example.estacaometeorologica.controller.dto;

import com.example.estacaometeorologica.model.DadosColetados;

import java.util.List;

public class DadosColetadosRecentesDto {

    private List<DadosColetados> registros;

    public DadosColetadosRecentesDto(List<DadosColetados> registros) {
        this.registros = registros;
    }

    public List<DadosColetados> getRegistros() {
        return registros;
    }
}
