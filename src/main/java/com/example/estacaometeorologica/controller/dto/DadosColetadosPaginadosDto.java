package com.example.estacaometeorologica.controller.dto;

import com.example.estacaometeorologica.model.DadosColetados;

import java.util.List;

public class DadosColetadosPaginadosDto {

    private List<DadosColetados> registros;
    private String paginacao;
    private int total_de_paginas;

    public DadosColetadosPaginadosDto(List<DadosColetados> registros, String paginacao, int total_de_paginas) {
        this.registros = registros;
        this.paginacao = paginacao;
        this.total_de_paginas = total_de_paginas;
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

    public int getTotal_de_paginas() {
        return total_de_paginas;
    }

    public void setTotal_de_paginas(int total_de_paginas) {
        this.total_de_paginas = total_de_paginas;
    }
}
