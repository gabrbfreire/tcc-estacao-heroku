package com.example.estacaometeorologica.controller.dto;

import com.example.estacaometeorologica.model.DadosColetados;

import java.util.List;

public class DadosColetadosPaginadosDto {

    private List<DadosColetados> registros;
    private String paginacao;

    public DadosColetadosPaginadosDto(List<DadosColetados> registros, String paginacao) {
        this.registros = registros;
        this.paginacao = paginacao;
    }

    public List<DadosColetados> getRegistros() {
        return registros;
    }

    public void setRegistros(List<DadosColetados> registros) {
        this.registros = registros;
    }

    public String getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(String paginacao) {
        this.paginacao = paginacao;
    }
}
