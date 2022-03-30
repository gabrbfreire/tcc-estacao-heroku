package com.example.estacaometeorologica.enums;

import com.example.estacaometeorologica.exception.TipoRelatorioInvalidoException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum TipoRelatorio {
    PDF("pdf"), CSV("csv");

    private String nome;

    TipoRelatorio(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public static TipoRelatorio from(String nome) {
        return Stream.of(TipoRelatorio.values())
                .filter(tipoRelatorio -> tipoRelatorio.getNome().equals(nome))
                .findFirst()
                .orElseThrow(TipoRelatorioInvalidoException::new);
    }
}
