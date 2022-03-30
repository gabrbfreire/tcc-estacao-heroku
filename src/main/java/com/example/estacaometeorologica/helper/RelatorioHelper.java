package com.example.estacaometeorologica.helper;

import com.example.estacaometeorologica.controller.dto.RelatoriosFilter;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RelatorioHelper {

    private RelatorioHelper() {
        //NOOP
    }

    public static String buildNomeRelatorio(RelatoriosFilter filter) {
        StringBuilder nomeRelatorio = new StringBuilder("santa-clima_");
        Optional.ofNullable(filter.getDataInicial()).ifPresent(data -> nomeRelatorio.append(data.format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss"))));
        Optional.ofNullable(filter.getDataFinal()).ifPresent(data -> nomeRelatorio.append(data.format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss"))));

        nomeRelatorio.append(".").append(filter.getTipo());

        return nomeRelatorio.toString();
    }
}
