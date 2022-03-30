package com.example.estacaometeorologica.exception;

public class TipoRelatorioInvalidoException extends RuntimeException {
    public TipoRelatorioInvalidoException(String message) {
        super(message);
    }

    public TipoRelatorioInvalidoException() {
        this("Tipo de relatório inválido.");
    }
}
