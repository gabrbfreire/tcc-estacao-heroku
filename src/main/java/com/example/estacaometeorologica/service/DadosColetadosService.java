package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.controller.dto.DadosColetadosDto;
import com.example.estacaometeorologica.controller.dto.TTNUplinkDto;
import com.example.estacaometeorologica.controller.form.DadosColetadosForm;
import com.example.estacaometeorologica.mapper.DadosColetadosMapper;
import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.repository.DadosColetadosRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DadosColetadosService {

    @Autowired
    private DadosColetadosRepository dadosColetadosRepository;

    public List<DadosColetados> getDadosColetados(){
        return dadosColetadosRepository.findAllByOrderByDataDesc();
    }

    public void saveDadosColetados(TTNUplinkDto dto) {
        try {
            dadosColetadosRepository.save(DadosColetadosMapper.mapToEntity(dto));
        }
        catch (Exception e) {
            System.out.println(e);
            throw e;
        }

    }

    public DadosColetados getDadoColetadoInseridoMaisRecente() {
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
