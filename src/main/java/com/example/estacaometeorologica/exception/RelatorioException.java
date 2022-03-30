package com.example.estacaometeorologica.exception;

public class RelatorioException extends RuntimeException {
    public RelatorioException(String message) {
        super(message);
    }

    public RelatorioException() {
        this("Houve um erro ao gerar o relatório de dados coletados.");
    }

    public RelatorioException(Throwable throwable) {
        this("Houve um erro ao gerar o relatório de dados coletados. " + throwable.getMessage());
    }
}
