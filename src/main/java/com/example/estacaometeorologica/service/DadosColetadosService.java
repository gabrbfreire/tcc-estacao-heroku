package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.config.Constants;
import com.example.estacaometeorologica.controller.dto.TTNUplinkDto;
import com.example.estacaometeorologica.mapper.DadosColetadosMapper;
import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.repository.DadosColetadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DadosColetadosService {

    @Autowired
    private DadosColetadosRepository dadosColetadosRepository;

    public List<DadosColetados> getDadosColetados(){
        return dadosColetadosRepository.findAllByOrderByDataDesc();
    }

    public void saveDadosColetados(TTNUplinkDto dto) {
        try {
            DadosColetados dadosColetados = DadosColetadosMapper.mapToEntity(dto);
            corrigirValoresAnormais(dadosColetados);
            dadosColetadosRepository.save(dadosColetados);
        }
        catch (Exception e) {
            System.out.println(e);
            throw e;
        }

    }

    private void corrigirValoresAnormais(DadosColetados dadosColetados) {
        //Por hora, só foi verificado uma anormalidade no sensor de pressão atmosférica.
        if (Double.compare(dadosColetados.getPressao_atmosferica(), Constants.VALOR_LIMITE_PRESSAO_ATMOSFERICA) < 0) {
            Double mediaAritmeticaDaPressaoAtmosfericaDosDoisUltimosRegistros = dadosColetadosRepository.findFirst2ByOrderByDataDesc()
                    .stream()
                    .mapToDouble(DadosColetados::getPressao_atmosferica)
                    .average().orElse(Double.NaN);
            dadosColetados.setPressao_atmosferica(mediaAritmeticaDaPressaoAtmosfericaDosDoisUltimosRegistros);
        }
    }

    public DadosColetados getUltimoDadoColetadoInserido() {
        return dadosColetadosRepository.findFirstByOrderByDataDesc();
    }

    public Page<DadosColetados> getDadosColetadosPorData(LocalDateTime data_inicial, LocalDateTime data_final, Pageable paginacao) {
        return dadosColetadosRepository.findByDataBetweenOrderByDataDesc(data_inicial, data_final, paginacao);
    }

    public List<DadosColetados> getDadosColetadosRecentes() {
        Pageable paginacao = PageRequest.of(0, 10);
        return dadosColetadosRepository.findAllByOrderByDataDesc(paginacao);
    }
}
