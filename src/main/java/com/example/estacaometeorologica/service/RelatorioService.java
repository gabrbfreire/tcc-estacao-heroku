package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.controller.dto.RelatoriosFilter;
import com.example.estacaometeorologica.enums.TipoRelatorio;
import com.example.estacaometeorologica.exception.RelatorioException;
import com.example.estacaometeorologica.exception.TipoRelatorioInvalidoException;
import com.example.estacaometeorologica.mapper.DadosColetadosMapper;
import com.example.estacaometeorologica.mapper.dto.DadosColetadosCSV;
import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.repository.DadosColetadosRepository;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@Service
public class RelatorioService {

    @Autowired
    DadosColetadosRepository dadosColetadosRepository;

    public void writeReport(RelatoriosFilter filter, Writer writer) {
        final List<DadosColetados> dadosColetados = dadosColetadosRepository
                .findByDataBetweenOrderByDataDesc(filter.getDataInicial(), filter.getDataFinal());

        final List<DadosColetadosCSV> dadosColetadosCSV = DadosColetadosMapper.mapToCSVList(dadosColetados);

        if (TipoRelatorio.CSV.equals(TipoRelatorio.from(filter.getTipo()))) {
            try {
                StatefulBeanToCsv<DadosColetadosCSV> beanToCsv = new StatefulBeanToCsvBuilder<DadosColetadosCSV>(writer)
                        .build();
                criarHeader(writer);
                beanToCsv.write(dadosColetadosCSV);
                writer.close();
            }
            catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                throw new RelatorioException(e);
            }
            return;
        }
        throw new TipoRelatorioInvalidoException();
    }

    private void criarHeader(Writer writer) throws IOException {
        writer.append("Data,Dir. Vento (°),Vel. Vento (Km/h),Precipit. (mm),Temp. (°C),Umidade (%),Press. Atm. (hPa)");
        writer.append("\n");
    }

}
